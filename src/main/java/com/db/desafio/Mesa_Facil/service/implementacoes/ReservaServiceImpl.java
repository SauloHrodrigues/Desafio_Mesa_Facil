package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import com.db.desafio.Mesa_Facil.exceptions.reserva.ReservaJaExisteException;
import com.db.desafio.Mesa_Facil.mapper.ReservaMapper;
import com.db.desafio.Mesa_Facil.model.Cliente;
import com.db.desafio.Mesa_Facil.model.Reserva;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import com.db.desafio.Mesa_Facil.repository.ReservaRepository;
import com.db.desafio.Mesa_Facil.service.ReservaServiceI;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservaServiceImpl implements ReservaServiceI {

    private final ReservaRepository repository;

    private final ClienteServiceImpl clienteService;

    private final RestauranteServiceImpl restauranteService;

    private ReservaMapper mapper = ReservaMapper.INSTANCE;


    @Override
    public ReservaResponse agendarReserva(NovaReserva dto) {
        Cliente cliente = clienteService.buscarCliente(dto.clienteId());
        Restaurante restaurante = restauranteService.buscarRestaurante(dto.restauranteId());
        validaReserva(cliente,restaurante,dto);
        Reserva reserva = mapper.toEntity(dto.dataHora(),cliente,restaurante);
        Reserva reservaSalva =repository.save(reserva);
        return mapper.toResponse(reservaSalva);
    }

    @Override
    public Page<ReservaResponse> listaDeReservas(Pageable pageable) {
        Page<ReservaResponse> reservas = repository.findAll(pageable).map(mapper::toResponse);
        return reservas;
    }

    private void validaReserva(Cliente cliente, Restaurante restaurante, NovaReserva dto){
        Optional<Reserva> reserva = repository.findByClienteAndRestauranteAndDataHora(cliente,
                restaurante, dto.dataHora().truncatedTo(ChronoUnit.MINUTES));

        if(reserva.isPresent()){
            throw new ReservaJaExisteException("O cliente: "+cliente.getNome().toUpperCase()+
                    " já tem reserva no restaurante: "+reserva.get());
        }
    }


}
