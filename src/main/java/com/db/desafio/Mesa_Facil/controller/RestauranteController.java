package com.db.desafio.Mesa_Facil.controller;

import com.db.desafio.Mesa_Facil.controller.interfaceSwagger.RestauranteSwaggesDoc;
import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import com.db.desafio.Mesa_Facil.service.RestauranteServiceI;
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
@RequestMapping(value = "/restaurantes")
public class RestauranteController implements RestauranteSwaggesDoc {

    private final RestauranteServiceI service;
    @Override
    @PostMapping
    public ResponseEntity<RestauranteResponseDto> cadastrar(@Valid @RequestBody NovoRestauranteDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }
}
