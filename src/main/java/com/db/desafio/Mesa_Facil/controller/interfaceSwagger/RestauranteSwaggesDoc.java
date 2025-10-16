package com.db.desafio.Mesa_Facil.controller.interfaceSwagger;

import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Restaturante",description = "Endpoints para gerenciar restaurantes")
public interface RestauranteSwaggesDoc {

    @Operation(summary = "Receber novo restaurante")
    @ApiResponse(responseCode = "201", description = "Cadastrar um novo restaurante com sucesso.",
            content = @Content(schema = @Schema(implementation = NovoRestauranteDto.class)))
    ResponseEntity<RestauranteResponseDto> cadastrar(@Valid @RequestBody NovoRestauranteDto dto);
}
