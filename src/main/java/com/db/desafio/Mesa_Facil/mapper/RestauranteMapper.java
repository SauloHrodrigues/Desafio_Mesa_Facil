package com.db.desafio.Mesa_Facil.mapper;

import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;
import com.db.desafio.Mesa_Facil.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestauranteMapper {

    RestauranteMapper INSTANCE = Mappers.getMapper(RestauranteMapper.class);

    @Mapping(target = "nome", expression = "java(dto.nome() != null ? dto.nome().toLowerCase() : null)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mesasOcupadas", constant = "0")
    @Mapping(target = "reservas", ignore = true)
    Restaurante toEntity(NovoRestauranteDto dto);

    RestauranteResponseDto toResponse(Restaurante restaurante);
}
