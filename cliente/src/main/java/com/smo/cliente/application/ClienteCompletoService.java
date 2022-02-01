package com.smo.cliente.application;

import com.smo.cliente.application.validator.IValidatorCliente;
import com.smo.cliente.domain.Cliente;
import com.smo.cliente.domain.imagen.ImagenModel;
import com.smo.cliente.infrastructure.client.ImagenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import  com.smo.cliente.application.ClienteService;

import java.io.IOException;

@Service
public class ClienteCompletoService {



    @Autowired
    ImagenClient imagenClient;

    @Autowired
    IValidatorCliente iValidatorCliente;

    Gson gson = new Gson();

    public ResponseEntity<Object> guardarClienteCompleto(String clienteModel, MultipartFile multipartFile) throws IOException {
        Cliente cliente = gson.fromJson(clienteModel,Cliente.class);
        iValidatorCliente.validartorCliente(cliente);
        //Falta validar imagen

        return imagenClient.guardarClienteImagenMongo(cliente.getCliNumDoc().trim(), multipartFile);
    }
}
