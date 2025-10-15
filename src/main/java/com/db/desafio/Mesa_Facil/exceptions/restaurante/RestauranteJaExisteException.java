package com.db.desafio.Mesa_Facil.exceptions.restaurante;

public class RestauranteJaExisteException extends RuntimeException{
    public RestauranteJaExisteException(String mensagem){
        super(mensagem);
    }
}