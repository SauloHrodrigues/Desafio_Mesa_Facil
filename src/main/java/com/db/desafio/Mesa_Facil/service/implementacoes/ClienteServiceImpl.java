package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import com.db.desafio.Mesa_Facil.exceptions.cliente.ClienteJaExisteException;
import com.db.desafio.Mesa_Facil.mapper.ClienteMapper;
import com.db.desafio.Mesa_Facil.model.Cliente;
import com.db.desafio.Mesa_Facil.repository.ClienteRepository;
import com.db.desafio.Mesa_Facil.service.ClienteServiceI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteServiceI {

    private final ClienteRepository repository;
    private ClienteMapper mapper = ClienteMapper.INSTANCE;

    @Override
    public ClienteResponseDto cadastrar(NovoClienteDto dto) {
        validarCadastro(dto);
        Cliente cliente = mapper.toEntity(dto);
        Cliente clienteSalvo = repository.save(cliente);
        return mapper.toResponse(clienteSalvo);
    }

    private void validarCadastro(NovoClienteDto dto){
        Optional<Cliente> cliente = repository.findByNomeIgnoreCase(dto.nome());
        if(cliente.isPresent()){
            throw new ClienteJaExisteException("O cliente: "+
                    cliente.get().getNome()+" já esta cadastrado no nosso banco de dados." +
                    " Seu id é: "+ cliente.get().getId());
        }
    }
}
