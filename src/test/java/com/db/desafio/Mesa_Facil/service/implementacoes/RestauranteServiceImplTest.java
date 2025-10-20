package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import com.db.desafio.Mesa_Facil.exceptions.restaurante.RestauranteJaExisteException;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import com.db.desafio.Mesa_Facil.repository.RestauranteRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceImplTest {

    @InjectMocks
    private RestauranteServiceImpl service;

    @Mock
    private RestauranteRepository repository;

    @Test
    @DisplayName("Deve realizar o cadastro de um novo restaurante com sucesso.")
    void cadastrar() {
        NovoRestauranteDto requestDto = new NovoRestauranteDto("Vilani",50);
        Restaurante restaurante = new Restaurante(1L,"vilani",50,0,null);

        when(repository.findByNomeIgnoreCase(requestDto.nome())).thenReturn(Optional.empty());
        when(repository.save(any(Restaurante.class))).thenReturn(restaurante);

        RestauranteResponseDto resposta = service.cadastrar(requestDto);

        assertNotNull(resposta.id());
        assertEquals(requestDto.nome().toLowerCase(),resposta.nome());
        assertEquals(requestDto.quantidadeDeMesas(),resposta.quantidadeDeMesas());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastrar um restaurante que já existe no banco ")
    void lancarExcecaoAoCadastrar() {
        NovoRestauranteDto requestDto = new NovoRestauranteDto("Vilani",50);
        Restaurante restaurante = new Restaurante(1L,"vilani",50,0,null);

        when(repository.findByNomeIgnoreCase(requestDto.nome())).thenReturn(Optional.of(restaurante));

        RestauranteJaExisteException exception = assertThrows(RestauranteJaExisteException.class,
                ()-> {service.cadastrar(requestDto);
        });

        assertTrue(exception.getMessage().contains(requestDto.nome().toUpperCase()));
        assertTrue(exception.getMessage().contains(restaurante.getId()+""));
        assertTrue(exception.getMessage().contains("já está cadastrado no sistema."));
    }

}