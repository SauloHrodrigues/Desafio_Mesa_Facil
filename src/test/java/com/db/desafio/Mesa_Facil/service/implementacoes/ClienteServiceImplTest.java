package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import com.db.desafio.Mesa_Facil.exceptions.cliente.ClienteJaExisteException;
import com.db.desafio.Mesa_Facil.model.Cliente;
import com.db.desafio.Mesa_Facil.repository.ClienteRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repository;

    @Test
    @DisplayName("Deve realizar o cadastro de um novo cliente com sucesso.")
    void cadastrar() {
        NovoClienteDto requestDto = new NovoClienteDto("João da silva");
        Cliente cliente =new Cliente(1L, "joão da silva",null);

        when(repository.findByNomeIgnoreCase(requestDto.nome())).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteResponseDto resposta = service.cadastrar(requestDto);

        assertNotNull(resposta.id());
        assertEquals(requestDto.nome().toLowerCase(),resposta.nome());

        verify(repository).findByNomeIgnoreCase(requestDto.nome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastrar um cliente que já existe no banco ")
    void lancarExcecaoAoCadastrar(){
        NovoClienteDto requestDto = new NovoClienteDto("João da silva");
        Cliente cliente =new Cliente(1L, "joão da silva",null);

        when(repository.findByNomeIgnoreCase(requestDto.nome())).thenReturn(Optional.of(cliente));

        ClienteJaExisteException exception= assertThrows( ClienteJaExisteException.class,
        ()-> {
            service.cadastrar(requestDto);
        });

        assertTrue(exception.getMessage().contains(requestDto.nome().toUpperCase()));
        assertTrue(exception.getMessage().contains(cliente.getId()+""));
        assertTrue(exception.getMessage().contains("já esta cadastrado no nosso banco de dados."));
    }
}