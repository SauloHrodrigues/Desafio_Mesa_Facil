package com.db.desafio.Mesa_Facil.dtos.reserva;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import com.db.desafio.Mesa_Facil.model.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ReservaResponse (
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataHora,
        String nomeDoCliente,
        String nomeDoRestaurante

)
{}