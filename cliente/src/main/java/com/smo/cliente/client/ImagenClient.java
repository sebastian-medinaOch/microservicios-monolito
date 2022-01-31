package com.smo.cliente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//Si no funciona así, debemos probar con registry-service
@FeignClient(name = "imagen-service")
public interface ImagenClient {

    @DeleteMapping(path = "/clienteimagen/v1/eliminarimagen/{cliimgnum}")
    public ResponseEntity<Object> eliminarCliImg(@PathVariable("cliimgnum") String cliimgnum) throws Exception;

}
