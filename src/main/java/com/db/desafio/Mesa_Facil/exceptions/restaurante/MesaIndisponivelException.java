package com.db.desafio.Mesa_Facil.exceptions.restaurante;

public class MesaIndisponivelException extends RuntimeException{
    public MesaIndisponivelException(String mensagem){
        super(mensagem);
    }
}