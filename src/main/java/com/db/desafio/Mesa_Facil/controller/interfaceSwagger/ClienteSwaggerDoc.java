package com.db.desafio.Mesa_Facil.controller.interfaceSwagger;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Clientes", description = "Endpoints para gerenciar clientes")
public interface ClienteSwaggerDoc {
    @Operation(summary = "Receber um novo cliente")
    @ApiResponse(responseCode = "201", description = "Cadastrar um novo cliente com sucesso.",
            content = @Content(schema = @Schema(implementation = NovoClienteDto.class)))
    ResponseEntity<ClienteResponseDto> cadastrar(@Valid @RequestBody NovoClienteDto dto);
}