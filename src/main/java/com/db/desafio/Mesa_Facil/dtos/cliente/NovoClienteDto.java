package com.db.desafio.Mesa_Facil.dtos.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "DTO para cadastrar um novo cliente.")
public record NovoClienteDto(

        @Schema(description = "Nome do cliente a ser cadastrado", example = "Paulo Silva")
        @NotBlank(message = "O nome do cliente é campo de preenchimento obrigatório.")
        @Pattern(
                regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)+$",
                message = "O nome deve conter pelo menos dois nomes."
        )
        String nome
) {
}
