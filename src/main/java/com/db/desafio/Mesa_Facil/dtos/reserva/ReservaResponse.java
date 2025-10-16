package com.db.desafio.Mesa_Facil.dtos.reserva;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import com.db.desafio.Mesa_Facil.model.Cliente;
import java.time.LocalDateTime;

public record ReservaResponse (
        Long id,
        LocalDateTime data,
        ClienteResponseDto cliente,
        RestauranteResponseDto restaurante

)
{}