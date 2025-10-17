package com.db.desafio.Mesa_Facil.mapper;

import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import com.db.desafio.Mesa_Facil.model.Cliente;
import com.db.desafio.Mesa_Facil.model.Reserva;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservaMapper {

    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "restaurante", source = "restaurante")
    @Mapping(target = "dataHora", source = "dataHora")
    Reserva toEntity(LocalDateTime dataHora, Cliente cliente, Restaurante restaurante);

    @Mapping(target = "nomeDoCliente", source = "cliente.nome")
    @Mapping(target = "nomeDoRestaurante", source = "restaurante.nome")
    ReservaResponse toResponse(Reserva reserva);
    List<ReservaResponse> toResponse(List<Reserva> reserva);


}
