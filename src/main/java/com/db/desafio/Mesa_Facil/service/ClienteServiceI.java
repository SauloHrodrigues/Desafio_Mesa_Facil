package com.db.desafio.Mesa_Facil.service;

import com.db.desafio.Mesa_Facil.dtos.cliente.ClienteResponseDto;
import com.db.desafio.Mesa_Facil.dtos.cliente.NovoClienteDto;

public interface ClienteServiceI {
    ClienteResponseDto cadastrar(NovoClienteDto dto);
}
