package com.db.desafio.Mesa_Facil.controller.interfaceSwagger;

import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

@Tag(name = "Reservas", description = "Endpoints para gerenciar reservas")
public interface ReservaSwaggerDoc {

    @Operation(summary = "Receber um nova reserva")
    @ApiResponse(responseCode = "201", description = "Cadastrar uma nova reserva com sucesso.",
            content = @Content(schema = @Schema(implementation = ReservaResponse.class)))
    ResponseEntity<ReservaResponse> cadastrar(NovaReserva dto);

    @Operation(summary = "Retorna todas as reservas")
    @ApiResponse(responseCode = "200", description = "Retorna uma lista com todas as reservas do banco de dados.",
            content = @Content(schema = @Schema(implementation = ReservaResponse.class)))
    ResponseEntity<Page<ReservaResponse>> listarReservas(
            @Parameter(description = "Parâmetros de paginação e ordenação")
            @PageableDefault(size = 10, sort = {"data"}) Pageable pageable);
}