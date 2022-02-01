package com.smo.cliente.infrastructure.client;

import com.smo.cliente.domain.imagen.ImagenModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "imagen-service")
public interface ImagenClient {

    @DeleteMapping(path = "imagenes/eliminarimg/{cliimgnum}")
    public ResponseEntity<Object> eliminarCliImg(@PathVariable("cliimgnum") String cliimgnum) throws Exception;

    @PostMapping("/crear")
    public ResponseEntity<Object> guardarClienteImagenMongo(String cliImgNum, MultipartFile multipartFile) throws IOException;

}
