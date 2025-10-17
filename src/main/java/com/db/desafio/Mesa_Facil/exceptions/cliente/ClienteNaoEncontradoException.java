package com.db.desafio.Mesa_Facil.exceptions.cliente;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}