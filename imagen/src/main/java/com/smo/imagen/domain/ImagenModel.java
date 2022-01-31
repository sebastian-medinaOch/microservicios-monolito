package com.smo.imagen.domain;

import com.smo.imagen.domain.cliente.ClienteModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Imagen")
@Data
public class ImagenModel {

    private String cliImgNum;

    private String cliImgUrl;
    private String cloIdImg;

    @Transient
    private ClienteModel clienteModel;

}
