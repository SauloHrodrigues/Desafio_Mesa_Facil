package com.db.desafio.Mesa_Facil.controller;

import com.db.desafio.Mesa_Facil.controller.interfaceSwagger.ClienteSwaggerDoc;
import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import com.db.desafio.Mesa_Facil.service.ClienteServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/clientes")
public class ClienteController implements ClienteSwaggerDoc {

    private final ClienteServiceI serviceI;

    @Override
    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastrar(@RequestBody @Valid NovoClienteDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceI.cadastrar(dto));
    }
}