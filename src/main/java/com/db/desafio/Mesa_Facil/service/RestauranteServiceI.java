package com.db.desafio.Mesa_Facil.service;

import com.db.desafio.Mesa_Facil.dtos.restaurante.NovoRestauranteDto;
import com.db.desafio.Mesa_Facil.dtos.restaurante.RestauranteResponseDto;

public interface RestauranteServiceI {

    RestauranteResponseDto cadastrar(NovoRestauranteDto dto);
}
