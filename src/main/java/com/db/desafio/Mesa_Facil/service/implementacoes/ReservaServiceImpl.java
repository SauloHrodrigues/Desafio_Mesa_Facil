package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import com.db.desafio.Mesa_Facil.exceptions.reserva.ReservaJaExisteException;
import com.db.desafio.Mesa_Facil.exceptions.reserva.ReservaNaoExisteException;
import com.db.desafio.Mesa_Facil.exceptions.restaurante.MesaIndisponivelException;
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
        restaurante.ocuparMesa();
        restaurante = restauranteService.salvarRestaurante(restaurante);
        Reserva reserva = mapper.toEntity(dto.dataHora(),cliente,restaurante);
        reserva  = repository.save(reserva);
        return mapper.toResponse(reserva);
    }

    @Override
    public Page<ReservaResponse> listaDeReservas(Pageable pageable) {
        Page<ReservaResponse> reservas = repository.findAll(pageable).map(mapper::toResponse);
        return reservas;
    }

    @Override
    public void cancelarReserva(Long id){
        Reserva reserva = buscarReserva(id);
        reserva.getRestaurante().liberarMesa();
        restauranteService.salvarRestaurante(reserva.getRestaurante());
        repository.delete(reserva);
    }

    protected Reserva buscarReserva(Long id){
        Optional<Reserva> reserva = repository.findById(id);

        if(reserva.isPresent()){
            return reserva.get();
        } else {
            throw new ReservaNaoExisteException("A reserva id: "+id+
                    " não corresponde a nenhuma reserva do nosso banco de dados;");
        }
    }

    protected void validaReserva(Cliente cliente, Restaurante restaurante, NovaReserva dto){
        Optional<Reserva> reserva = repository.findByClienteAndRestauranteAndDataHora(cliente,
                restaurante, dto.dataHora().truncatedTo(ChronoUnit.MINUTES));

        if(reserva.isPresent()){
            throw new ReservaJaExisteException("O cliente: "+cliente.getNome().toUpperCase()+
                    " já tem reserva no restaurante: "+reserva.get());
        }
        validarMesaLivre(restaurante);
    }

    protected void validarMesaLivre(Restaurante restaurante){
        if (restaurante.getQuantidadeDeMesas() < restaurante.getMesasOcupadas()){
            throw new MesaIndisponivelException("O restaurante: "+
                    restaurante.getNome().toUpperCase()+" está sem mesa disponivel;");
        }
    }
}