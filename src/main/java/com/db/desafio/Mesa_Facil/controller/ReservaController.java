package com.db.desafio.Mesa_Facil.controller;

import com.db.desafio.Mesa_Facil.controller.interfaceSwagger.ReservaSwaggerDoc;
import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reservas")
public class ReservaController implements ReservaSwaggerDoc {



    @Override
    @PostMapping
    public ResponseEntity<ReservaResponse> cadastrar(NovaReserva dto) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ReservaResponse>> listarReservas(Pageable pageable) {
        return null;
    }
}
