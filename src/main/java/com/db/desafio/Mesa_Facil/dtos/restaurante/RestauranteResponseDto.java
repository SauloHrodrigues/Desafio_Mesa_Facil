package com.db.desafio.Mesa_Facil.dtos.restaurante;

import com.db.desafio.Mesa_Facil.model.Reserva;
import java.util.List;

public record RestauranteResponseDto(

        Long id,
        String nome,

        Integer quantidadeDeMesas,

        List<Reserva> reservas

) {
}
