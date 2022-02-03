package com.smo.cliente.infrastructure.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImagenHystrixFallbackFactory implements ImagenServiceClient{
    @Override
    public ResponseEntity<Object> guardarClienteImagenMongo(String cliImgNum, MultipartFile multipartFile) throws IOException {
        //Probar de devolver lo mismo que tiene la función
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio algún error con el microservico de " +
                "cliente");
    }

    @Override
    public ResponseEntity<Object> obtenerTodosImg() {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio algún error con el microservico de " +
                "cliente");
    }

    @Override
    public ResponseEntity<Object> eliminarImg(String cliimgnum) throws IOException {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Ocurrio algún error con el microservico de " +
                "cliente");
    }
}
