package com.smo.cliente.application.validator;

import java.io.IOException;
import java.util.ArrayList;

import com.smo.cliente.domain.Cliente;
import com.smo.cliente.domain.Answers.AnswerNotData;
import com.smo.cliente.domain.exceptions.ApiClienteInvalid;
import com.smo.cliente.infrastructure.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImplementsCliente implements IValidatorCliente{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void validartorCliente(Cliente clienteModel) throws IOException {
        clienteModel.setCliNumDoc(clienteModel.getCliNumDoc().trim());

        ArrayList<Cliente> obtenCliNumDoc = clienteRepository.findByCliNumDoc(clienteModel.getCliNumDoc().toString().trim());
        if (clienteModel.getCliNumDoc().isBlank() || clienteModel.getCliNom().isBlank() || clienteModel.getCliTipDoc().isBlank() || clienteModel.getCliEda().equals(0)  || clienteModel.getCliCiu().isBlank()) {
            throw new ApiClienteInvalid(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "No se pudo realizar el registro porque algún campo importante esta vació"));
        }else {
            if (clienteModel.getCliEda() < 0) {
                throw new ApiClienteInvalid(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "No se pudo realizar el registro porque la edad es menores de 0"));
            } else {
                if (obtenCliNumDoc.size() > 0) {
                    throw new ApiClienteInvalid(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "No se pudo realizar el registro porque ya existe un cliente con este numero de documento"));
                } 
            }
        }
    }
    
}
