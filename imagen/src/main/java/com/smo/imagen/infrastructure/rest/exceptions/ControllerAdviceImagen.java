package com.smo.imagen.infrastructure.rest.exceptions;

import com.smo.imagen.domain.answers.AnswerNotData;
import com.smo.imagen.domain.exceptions.ApiImageInvalid;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ControllerAdviceImagen {

    @ExceptionHandler(ApiImageInvalid.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> apiImageInvalid(ApiImageInvalid error){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error.getAnswerNotData());

    }

    //Exceptiones generales
    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> error(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "El archivo debe de ser una imagen"));
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> errorMax(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "El archivo debe de ser una imagen de menor tamaño"));
    }


    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> errorNumber(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "Un dato que solo permite numeros contiene letras"));
    }

}
