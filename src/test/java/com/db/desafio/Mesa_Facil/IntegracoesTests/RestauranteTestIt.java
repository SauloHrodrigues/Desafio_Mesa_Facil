package com.db.desafio.Mesa_Facil.IntegracoesTests;

import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestauranteTestIt {
    @Autowired
    private TestRestTemplate template;

    @Test
    @DisplayName("Deve cadastrar um restaurante com sucesso!")
    public void cadastrarNovoRestauranteComSucesso() {
        NovoRestauranteDto dto = new NovoRestauranteDto("kilo come", 5);
        ResponseEntity<RestauranteResponseDto> resposta = template.postForEntity(
                "/restaurantes", dto, RestauranteResponseDto.class);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resposta.getBody().id()).isNotNull();
        assertThat(resposta.getBody().nome()).isEqualTo(dto.nome().toLowerCase());
        assertThat(resposta.getBody().quantidadeDeMesas()).isEqualTo(5);
    }
}