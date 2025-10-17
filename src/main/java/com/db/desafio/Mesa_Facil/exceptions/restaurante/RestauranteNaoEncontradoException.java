package com.db.desafio.Mesa_Facil.exceptions.restaurante;

public class RestauranteNaoEncontradoException extends RuntimeException{
    public RestauranteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}