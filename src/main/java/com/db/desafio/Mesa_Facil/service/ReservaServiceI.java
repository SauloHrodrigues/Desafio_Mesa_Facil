package com.db.desafio.Mesa_Facil.service;

import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservaServiceI {
    ReservaResponse agendarReserva(NovaReserva dto);
    Page<ReservaResponse> listaDeReservas(Pageable pageable);
    void cancelarReserva(Long id);
}