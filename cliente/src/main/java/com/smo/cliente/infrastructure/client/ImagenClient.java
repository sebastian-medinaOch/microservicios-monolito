package com.smo.cliente.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@FeignClient(name = "imagen-service")
@RequestMapping("/clienteimagen/v1")
public interface ImagenClient {

    @DeleteMapping(path = "/eliminarimagen/{cliimgnum}")
    public ResponseEntity<Object> eliminarCliImg(@PathVariable("cliimgnum") String cliimgnum) throws Exception;

}
