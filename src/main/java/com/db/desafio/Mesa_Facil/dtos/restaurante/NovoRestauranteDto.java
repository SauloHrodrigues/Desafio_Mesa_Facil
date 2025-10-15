package com.db.desafio.Mesa_Facil.dtos.restaurante;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para cadastrar um novo restaurante.")
public record NovoRestauranteDto(

        @Schema(description = "Nome do restaurante a ser cadastrado", example = "Kilo feliz")
        @NotBlank(message = "O nome do restaurante é de preenchimento obrigatório.")
        String nome,

        @Schema(description = "Quantidade de mesas existentes no restaurante.", examples = "42")
        @NotNull(message = "O número de mesas do restaurante é de preenchimento obrigatório.")
        Integer quantidadeDeMesas
) {
}
