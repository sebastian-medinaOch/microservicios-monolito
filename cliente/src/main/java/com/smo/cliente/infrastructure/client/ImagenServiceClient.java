package com.smo.cliente.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@FeignClient(name = "imagen-service")
public interface ImagenServiceClient {


    //@PostMapping("imagenes")
    //public ResponseEntity<Object> guardarClienteImagenMongo(String cliImgNum, MultipartFile multipartFile) throws IOException ;

    @GetMapping("imagenes/obtenertodos")
    public ResponseEntity<Object> obtenerTodosImg();

    @DeleteMapping(path = "imagenes/eliminarimg/{cliimgnum}")
    public ResponseEntity<Object> eliminarImg(@PathVariable("cliimgnum") String cliimgnum) throws IOException;


}
