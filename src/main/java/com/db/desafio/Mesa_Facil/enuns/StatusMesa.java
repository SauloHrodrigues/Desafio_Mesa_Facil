package com.db.desafio.Mesa_Facil.enuns;

public enum StatusMesa {
    LIVRE("livre"),
    OCULPADA("Ocupada");

    private String status;
    StatusMesa(String status) {
        this.status = status;
    }
}