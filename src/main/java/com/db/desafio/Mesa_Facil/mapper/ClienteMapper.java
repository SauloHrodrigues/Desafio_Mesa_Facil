package com.db.desafio.Mesa_Facil.mapper;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;
import com.db.desafio.Mesa_Facil.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "nome", expression = "java(dto.nome() != null ? dto.nome().toLowerCase() : null)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservas", ignore = true)
    Cliente toEntity(NovoClienteDto dto);

    ClienteResponseDto toResponse(Cliente cliente);
}
