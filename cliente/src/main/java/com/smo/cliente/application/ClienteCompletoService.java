package com.smo.cliente.application;

import com.google.gson.Gson;
import com.smo.cliente.application.validator.IValidatorCliente;
import com.smo.cliente.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class ClienteCompletoService {

    Gson gson = new Gson();

    @Autowired
    IValidatorCliente iValidatorCliente;

    @Autowired
    ClienteService clienteService;


    public Cliente guardarClienteCompleto(String clientemodel, MultipartFile multipartFile) throws IOException {
        Cliente cliente = gson.fromJson(clientemodel, Cliente.class);

        iValidatorCliente.validartorCliente(cliente);

        return clienteService.guarCliente(cliente);

    }
}
