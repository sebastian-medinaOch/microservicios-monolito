package com.smo.cliente.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@FeignClient(name = "imagen-service", fallback = ImagenHystrixFallbackFactory.class)
public interface ImagenServiceClient {


    @PostMapping(value = "imagenes/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> guardarClienteImagenMongo(@RequestParam("cliImgNum") String cliImgNum ,
                                                            @RequestPart("multipartFile") MultipartFile multipartFile) throws
            IOException;

    @GetMapping("imagenes/obtenertodos")
    public ResponseEntity<Object> obtenerTodosImg();

    @DeleteMapping(path = "imagenes/eliminarimg/{cliimgnum}")
    public ResponseEntity<Object> eliminarImg(@PathVariable("cliimgnum") String cliimgnum) throws IOException;


}
