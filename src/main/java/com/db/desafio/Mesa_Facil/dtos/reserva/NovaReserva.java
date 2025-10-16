package com.db.desafio.Mesa_Facil.dtos.reserva;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
@Schema(description = "DTO para cadastrar uma nova reserva.")
public record NovaReserva(
        LocalDateTime data,
        Long client_id,
        Long restaurante_id
)
{}