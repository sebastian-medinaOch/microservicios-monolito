package com.smo.cliente.infrastructure.rest.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.smo.cliente.domain.Answers.AnswerNotData;
import com.smo.cliente.domain.exceptions.ApiClienteInvalid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviceCliente {
    
    //Exceptiones generales
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> errorNumber(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "Un dato que solo permite numeros contiene letras"));
    }

    // MalformedJsonException

    @ExceptionHandler(ApiClienteInvalid.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> apiClienteInvalid(ApiClienteInvalid error){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error.getAnswerNotData());
    }
    
}
