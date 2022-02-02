package com.smo.cloudinary.domain.answers;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Data
public class AnswerData {

    private LocalDateTime
            timestamp;
    private HttpStatus status;
    private Optional<Object> data;

    public AnswerData(HttpStatus status, Optional<Object> data){
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        this.timestamp = LocalDateTime.of(hoy,ahora);
        this.status = status;
        this.data = Optional.of(data.orElse("[]"));
    }

}