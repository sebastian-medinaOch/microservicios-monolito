package com.smo.cliente.application;

import com.google.gson.Gson;
import com.smo.cliente.application.validator.IValidatorCliente;
import com.smo.cliente.domain.Answers.AnswerNotData;
import com.smo.cliente.domain.Cliente;
import com.smo.cliente.domain.imagen.ImagenModel;
import com.smo.cliente.infrastructure.client.ImagenServiceClient;
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

    @Autowired
    ImagenServiceClient imagenServiceClient;

    //Preguntar a Cristhian sobre este error
    public ImagenModel guardarClienteCompleto(String clientemodel, MultipartFile multipartFile) throws IOException {

        Cliente cliente = gson.fromJson(clientemodel, Cliente.class);

        iValidatorCliente.validartorCliente(cliente);

        clienteService.guarCliente(cliente);
        //try {
            String imagen = imagenServiceClient.guardarClienteImagenMongo(cliente.getCliNumDoc().trim(),
                            multipartFile).getBody()
                    .toString().split("data=")[1].replace("{", "").replace("}", "");
            ImagenModel imagenModel = new ImagenModel();
            imagenModel.setCliImgNum(cliente.getCliNumDoc());
            imagenModel.setCliImgUrl(imagen.split(",")[1].trim());
            imagenModel.setCloIdImg(imagen.split(",")[2].trim());
            return imagenModel;
        //} catch (Exception e) {
            //String hp = e.getMessage().toString().split("]:")[1].replace("[","").replace("]","").trim().split(",")[1];
            //return new ImagenModel();
        //}
    }
}
