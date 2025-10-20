package com.db.desafio.Mesa_Facil.exceptions.reserva;

public class ReservaNaoExisteException extends RuntimeException{
    public ReservaNaoExisteException(String mensagem){
        super(mensagem);
    }
}