package com.db.desafio.Mesa_Facil.exceptions.cliente;

public class ClienteJaExisteException extends RuntimeException{
    public ClienteJaExisteException(String mensagem){
        super(mensagem);
    }
}