package com.db.desafio.Mesa_Facil.service.implementacoes;

import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import com.db.desafio.Mesa_Facil.exceptions.restaurante.RestauranteJaExisteException;
import com.db.desafio.Mesa_Facil.mapper.RestauranteMapper;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import com.db.desafio.Mesa_Facil.repository.RestauranteRepository;
import com.db.desafio.Mesa_Facil.service.RestauranteServiceI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestauranteServiceImpl implements RestauranteServiceI {

    private final RestauranteRepository repository;
    private RestauranteMapper mapper = RestauranteMapper.INSTANCE;

    @Override
    public RestauranteResponseDto cadastrar(NovoRestauranteDto dto) {
        validarCadastro(dto);
        Restaurante restaurante = mapper.toEntity(dto);
        Restaurante salvo = repository.save(restaurante);
        return mapper.toResponse(salvo);
    }

    private void validarCadastro(NovoRestauranteDto dto) {
        Optional<Restaurante> restaurante = repository.findByNomeIgnoreCase(dto.nome());
        if (restaurante.isPresent()) {
            throw new RestauranteJaExisteException(
                    "O restaurante " + dto.nome().toUpperCase() + " já está cadastrado no sistema. " +
                            "O id do restaurante é: " + restaurante.get().getId()
            );
        }
    }

    protected Restaurante buscarRestaurante(Long id){
        Optional<Restaurante> restaurante = repository.findById(id);

        if(restaurante.isPresent()){
            return restaurante.get();
        } else {
            throw new RestauranteJaExisteException("O id: "+id+
                    " não corresponde a nenhum restaurante do nosso banco de dados;");
        }
    }

    protected Restaurante salvarRestaurante(Restaurante restaurante){
        return repository.save(restaurante);
    }

}