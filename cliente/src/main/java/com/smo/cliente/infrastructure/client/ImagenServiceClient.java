package com.smo.cliente.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@FeignClient(name = "imagen-service")
public interface ImagenServiceClient {

    @DeleteMapping(path = "imagenes/eliminarimg/{cliimgnum}")
    public ResponseEntity<Object> eliminarImg(@PathVariable("cliimgnum") String cliimgnum) throws IOException;

}
