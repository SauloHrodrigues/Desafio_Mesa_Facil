package com.db.desafio.Mesa_Facil.exceptions;

import com.db.desafio.Mesa_Facil.exceptions.cliente.ClienteJaExisteException;
import com.db.desafio.Mesa_Facil.exceptions.cliente.ClienteNaoEncontradoException;
import com.db.desafio.Mesa_Facil.exceptions.reserva.ReservaJaExisteException;
import com.db.desafio.Mesa_Facil.exceptions.restaurante.RestauranteJaExisteException;
import com.db.desafio.Mesa_Facil.exceptions.restaurante.RestauranteNaoEncontradoException;
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
    @ExceptionHandler(ReservaJaExisteException.class)
    public ResponseEntity<Object>handlerReservaJaExisteException(ReservaJaExisteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR! "+ex.getMessage());
    }

@ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<Object>handlerClienteNaoEncontradoException(ClienteNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR! "+ex.getMessage());
    }
@ExceptionHandler(RestauranteNaoEncontradoException.class)
    public ResponseEntity<Object>handlerRestauranteNaoEncontradoException(RestauranteNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR! "+ex.getMessage());
    }
}