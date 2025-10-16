package com.db.desafio.Mesa_Facil.exceptions;

import com.db.desafio.Mesa_Facil.exceptions.cliente.ClienteJaExisteException;
import com.db.desafio.Mesa_Facil.exceptions.restaurante.RestauranteJaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestauranteJaExisteException.class)
    public ResponseEntity<Object>handlerRestauranteJaExisteException(RestauranteJaExisteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR! "+ex.getMessage());
    }
@ExceptionHandler(ClienteJaExisteException.class)
    public ResponseEntity<Object>handlerClienteJaExisteException(ClienteJaExisteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR! "+ex.getMessage());
    }
}