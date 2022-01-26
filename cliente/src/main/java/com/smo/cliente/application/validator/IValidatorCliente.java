package com.smo.cliente.application.validator;

import java.io.IOException;

import com.smo.cliente.domain.Cliente;

import org.springframework.stereotype.Service;

@Service
public interface IValidatorCliente {
    void validartorCliente(Cliente clienteModel)throws IOException;
}
