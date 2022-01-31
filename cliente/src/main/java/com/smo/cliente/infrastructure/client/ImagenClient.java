package com.smo.cliente.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "imagen-service")
public interface ImagenClient {

    @DeleteMapping(path = "imagenes/eliminarimg/{cliimgnum}")
    public ResponseEntity<Object> eliminarCliImg(@PathVariable("cliimgnum") String cliimgnum) throws Exception;

}
