package com.db.desafio.Mesa_Facil.dtos.cliente;

import com.db.desafio.Mesa_Facil.model.Reserva;
import java.util.List;

public record ClienteResponseDto(
        Long id,
        String nome,
        List<Reserva> reservas

) {
}
