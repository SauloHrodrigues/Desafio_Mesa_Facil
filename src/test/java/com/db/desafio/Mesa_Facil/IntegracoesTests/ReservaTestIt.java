package com.db.desafio.Mesa_Facil.IntegracoesTests;

import com.db.desafio.Mesa_Facil.auxiliar.PageResponse;
import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import com.db.desafio.Mesa_Facil.dtos.reserva.NovaReserva;
import com.db.desafio.Mesa_Facil.dtos.reserva.ReservaResponse;
import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservaTestIt {

    @Autowired
    private TestRestTemplate template;

    private NovoRestauranteDto restauranteDto;
    private NovoClienteDto clienteJoaoDto;
    private NovoClienteDto clienteMariaDto;
    private NovoClienteDto clientePauloDto;
    private  ResponseEntity<RestauranteResponseDto> restauranteNoBanco;
    private ResponseEntity<ClienteResponseDto> clienteJoaoNoBanco;
    private ResponseEntity<ClienteResponseDto> clienteMariaNoBanco;
    private ResponseEntity<ClienteResponseDto> clientePauloNoBanco;
    private DateTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        restauranteDto = new NovoRestauranteDto("kilo come", 5);
        restauranteNoBanco = template.postForEntity(
                "/restaurantes", restauranteDto, RestauranteResponseDto.class);
        clienteJoaoDto = new NovoClienteDto("João da silva");
        clienteMariaDto = new NovoClienteDto("maria Silva");
        clientePauloDto = new NovoClienteDto("paulo cezar");

        clienteJoaoNoBanco = template.postForEntity(
                "/clientes", clienteJoaoDto,ClienteResponseDto.class);
        clienteMariaNoBanco  = template.postForEntity(
                "/clientes", clienteMariaDto,ClienteResponseDto.class);
        clientePauloNoBanco =  template.postForEntity(
                "/clientes", clientePauloDto,ClienteResponseDto.class);

    }
    @Test
    @DisplayName("Deve cadastrar uma reserva com sucesso!")
    public void deveCadastrarNovoReservaComSucesso() {
        Long idCliente = clienteJoaoNoBanco.getBody().id();
        Long idRestaurante = restauranteNoBanco.getBody().id();

        NovaReserva reservaRequest = new NovaReserva(idCliente,idRestaurante,
                LocalDateTime.parse("12/09/2026 12:30",formatter));

        ResponseEntity<ReservaResponse> resposta = template.postForEntity(
                "/reservas",reservaRequest, ReservaResponse.class);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resposta.getBody().nomeDoCliente()).isEqualTo(clienteJoaoDto.nome().toLowerCase());
        assertThat(resposta.getBody().nomeDoRestaurante()).isEqualTo(restauranteDto.nome().toLowerCase());
        assertThat(resposta.getBody().dataHora()).isEqualTo(reservaRequest.dataHora());
    }

    @Test
    @DisplayName("Deve retornar uma lista de todas as resrvas cadastradas no banco.")
    public void deveRetornarListaDeReservaComSucesso() {
        Long idClienteJoao = clienteJoaoNoBanco.getBody().id();
        Long idClienteMaria = clienteMariaNoBanco.getBody().id();
        Long idClientePaulo = clientePauloNoBanco.getBody().id();
        Long idRestaurante = restauranteNoBanco.getBody().id();

        NovaReserva reservaRequest01 = new NovaReserva(idClienteJoao,idRestaurante, LocalDateTime.parse("12/09/2026 12:30",formatter));
        NovaReserva reservaRequest02 = new NovaReserva(idClienteMaria,idRestaurante, LocalDateTime.parse("12/09/2026 14:30",formatter));
        NovaReserva reservaRequest03 = new NovaReserva(idClientePaulo,idRestaurante, LocalDateTime.parse("12/09/2026 19:30",formatter));

        template.postForEntity("/reservas",reservaRequest01, ReservaResponse.class);
        template.postForEntity("/reservas",reservaRequest02, ReservaResponse.class);
        template.postForEntity("/reservas",reservaRequest03, ReservaResponse.class);

        ResponseEntity<PageResponse<ReservaResponse>> resposta =
                template.exchange(
                        "/reservas?page=0&size=3",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PageResponse<ReservaResponse>>() {
                        }
                );

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();
        assertThat(resposta.getBody().getContent()).hasSize(3);
        assertThat(resposta.getBody().getTotalElements()).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve Cancelar uma reserva cadastradas no banco.")
    public void deveCancelarUmaReservaComSucesso(){
        Long idClientePaulo = clientePauloNoBanco.getBody().id();
        Long idRestaurante = restauranteNoBanco.getBody().id();

        NovaReserva reservaRequest = new NovaReserva(idClientePaulo,idRestaurante, LocalDateTime.parse("12/09/2026 19:30",formatter));
        ResponseEntity<ReservaResponse> reserva = template.postForEntity("/reservas",reservaRequest, ReservaResponse.class);
        Long idReserva = reserva.getBody().id();

        ResponseEntity<Void> resposta = template.exchange(
                "/reservas/" + idReserva,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );

        assertThat(reserva.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}