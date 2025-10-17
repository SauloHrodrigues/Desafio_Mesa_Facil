package com.db.desafio.Mesa_Facil.dtos.reserva;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO para cadastrar uma nova reserva.")
public record NovaReserva(
        Long clienteId,
        Long restauranteId,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora
) {
}