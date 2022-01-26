package com.smo.cliente.domain.Answers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class AnswerNotData {

    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;


    public AnswerNotData(HttpStatus status, String message){
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        this.timestamp = LocalDateTime.of(hoy,ahora);
        this.status = status;
        this.message = message;
    }
    
}
