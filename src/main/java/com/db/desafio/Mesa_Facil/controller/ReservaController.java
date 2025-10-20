package com.db.desafio.Mesa_Facil.controller;

import com.db.desafio.Mesa_Facil.controller.interfaceSwagger.ReservaSwaggerDoc;
import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import com.db.desafio.Mesa_Facil.service.ReservaServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/reservas")
public class ReservaController implements ReservaSwaggerDoc {

    private final ReservaServiceI service;

    @Override
    @PostMapping
    public ResponseEntity<ReservaResponse> cadastrar(@Valid @RequestBody NovaReserva dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agendarReserva(dto));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ReservaResponse>> listarReservas(
            @PageableDefault(size = 10, sort = {"dataHora"}) Pageable pageable) {

        var resposta = service.listaDeReservas(pageable);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        service.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}