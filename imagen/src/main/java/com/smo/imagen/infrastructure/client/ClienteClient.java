package com.smo.imagen.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cliente-service")
public interface ClienteClient {

    @GetMapping(path = "clientes/querydos")
    public ResponseEntity<Object> obtenerPorNumDoc(@RequestParam("cliImgNum") String cliImgNum);

}
