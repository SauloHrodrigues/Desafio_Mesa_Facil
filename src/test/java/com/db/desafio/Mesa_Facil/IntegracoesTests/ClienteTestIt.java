package com.db.desafio.Mesa_Facil.IntegracoesTests;

import com.db.desafio.Mesa_Facil.auxiliar.PageResponse;
import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteTestIt {
    @Autowired
    private TestRestTemplate template;

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso!")
    public void cadastrarNovoCienteComSucesso(){
        NovoClienteDto dto= new NovoClienteDto("João da silva");
        ResponseEntity<ClienteResponseDto> resposta = template.postForEntity(
                "/clientes",dto,ClienteResponseDto.class);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resposta.getBody().id()).isNotNull();
        assertThat(resposta.getBody().nome()).isEqualTo(dto.nome().toLowerCase());

    }
}