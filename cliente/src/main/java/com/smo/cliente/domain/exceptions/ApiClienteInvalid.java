package com.smo.cliente.domain.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.smo.cliente.domain.Answers.AnswerNotData;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ApiClienteInvalid extends RuntimeException {
    AnswerNotData answerNotData;
    
    public ApiClienteInvalid(AnswerNotData answerNotData) {
        this.answerNotData = answerNotData;
    }

    public AnswerNotData getAnswerNotData() {
        return answerNotData;
    }

    public void setAnswerNotData(AnswerNotData answerNotData) {
        this.answerNotData = answerNotData;
    }

}
