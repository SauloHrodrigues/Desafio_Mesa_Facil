package com.db.desafio.Mesa_Facil.exceptions.reserva;

public class ReservaJaExisteException extends RuntimeException{
    public ReservaJaExisteException(String mensagem){
        super(mensagem);
    }
}