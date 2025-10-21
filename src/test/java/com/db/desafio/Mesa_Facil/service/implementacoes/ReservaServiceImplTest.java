package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import com.db.desafio.Mesa_Facil.exceptions.reserva.ReservaJaExisteException;
import com.db.desafio.Mesa_Facil.model.Cliente;
import com.db.desafio.Mesa_Facil.model.Reserva;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import com.db.desafio.Mesa_Facil.repository.ReservaRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservaServiceImplTest {

    @InjectMocks
    private ReservaServiceImpl service;

    @Mock
    private ReservaRepository repository;

    @Mock
    private ClienteServiceImpl clienteService;

    @Mock
    private RestauranteServiceImpl restauranteService;

    private Cliente cliente;
    private Restaurante restaurante;
    private Restaurante restauranteAtualizado;
    private NovaReserva novaReservaDto;
    private Reserva reserva;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @BeforeEach
    void setUp() {

        cliente = new Cliente(1L,"jose da silva",null);
        restaurante = new Restaurante(2L,"vilani",5,0,null);
        restauranteAtualizado = new Restaurante(2L,"vilani",4,1,null);
        novaReservaDto = new NovaReserva(1L,2L, LocalDateTime.parse("12/09/2026 12:30", formatter));
        reserva = new Reserva(1L,LocalDateTime.parse("12/09/2026 12:30",formatter),cliente,restaurante);
    }

    @Test
    @DisplayName("Deve realiza uma nova agenda com sucesso.")
    void agendarReserva() {
        Long idCliente = cliente.getId();
        Long idRestaurante = restaurante.getId();

        when(clienteService.buscarCliente(idCliente)).thenReturn(cliente);
        when(restauranteService.buscarRestaurante(idRestaurante)).thenReturn(restaurante);
        when(repository.findByClienteAndRestauranteAndDataHora(cliente,
                restaurante, novaReservaDto.dataHora().truncatedTo(ChronoUnit.MINUTES))).thenReturn(Optional.empty());
        when(restauranteService.salvarRestaurante(restaurante)).thenReturn(restauranteAtualizado);
        when(repository.save(any(Reserva.class))).thenReturn(reserva);

        ReservaResponse resposta = service.agendarReserva(novaReservaDto);

        assertNotNull(resposta.id());
        assertEquals(novaReservaDto.dataHora(),resposta.dataHora());
        assertEquals(cliente.getNome(),resposta.nomeDoCliente());
        assertEquals(restauranteAtualizado.getNome(),resposta.nomeDoRestaurante());

    }

    @Test
    @DisplayName("Deve lançar excessão ao tentar realizar uma nova agenda já existente.")
    void agendarReservaJaExistente() {
        Long idCliente = cliente.getId();
        Long idRestaurante = restaurante.getId();


        when(clienteService.buscarCliente(idCliente)).thenReturn(cliente);
        when(restauranteService.buscarRestaurante(idRestaurante)).thenReturn(restaurante);
        when(repository.findByClienteAndRestauranteAndDataHora(cliente,
                restaurante, novaReservaDto.dataHora().truncatedTo(ChronoUnit.MINUTES))).thenReturn(Optional.of(reserva));


        ReservaJaExisteException exception = assertThrows(ReservaJaExisteException.class, ()->{
            service.agendarReserva(novaReservaDto);
                });

        System.out.println(exception);
        assertTrue(exception.getMessage().contains(cliente.getNome().toUpperCase()));
        assertTrue(exception.getMessage().contains("já tem reserva no restaurante:"));
        assertTrue(exception.getMessage().contains(reserva.getId()+""));

    }

    @Test
    @DisplayName("Deve retornar uma lista de resrvas cadastradas.")
    void listaDeReservas() {
        Reserva reserva01 = new Reserva(2L,LocalDateTime.parse("13/09/2026 17:30",formatter),cliente,restaurante);
        Reserva reserva03 = new Reserva(3L,LocalDateTime.parse("14/09/2026 20:30",formatter),cliente,restaurante);
        List<Reserva> reservas = List.of(reserva,reserva01,reserva03);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Reserva> reservaResponses = new PageImpl<>(reservas,pageable,reservas.size());

        when(repository.findAll(any(Pageable.class))).thenReturn(reservaResponses);

        Page<ReservaResponse> resposta = service.listaDeReservas(pageable);

        assertEquals(3,resposta.getContent().size());
        assertTrue(resposta.getContent().stream().anyMatch(r -> r.id().equals(reserva.getId())));
        assertTrue(resposta.getContent().stream().anyMatch(r -> r.id().equals(reserva01.getId())));
        assertTrue(resposta.getContent().stream().anyMatch(r -> r.id().equals(reserva03.getId())));

        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    @DisplayName("Deve cancelar uma agenda já existente com sucesso.")
    void cancelarReserva() {

        when(repository.findById(reserva.getId())).thenReturn(Optional.of(reserva));

        service.cancelarReserva(reserva.getId());
        verify(repository).delete(reserva);

    }
}