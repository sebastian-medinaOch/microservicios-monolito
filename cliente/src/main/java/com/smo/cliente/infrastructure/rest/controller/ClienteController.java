package com.smo.cliente.infrastructure.rest.controller;

import java.io.IOException;
import java.util.Optional;

import com.smo.cliente.application.ClienteCompletoService;
import com.smo.cliente.application.ClienteService;
import com.smo.cliente.domain.Cliente;
import com.smo.cliente.domain.Answers.AnswerData;
import com.smo.cliente.domain.Answers.AnswerNotData;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @Autowired
    private ClienteCompletoService clienteCompletoService;


    @GetMapping()
    public ResponseEntity<Object> obtenerClientes() {
        if (clienteService.obtenerClientes().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se " +
                    "encontrarón clientes"));
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND,
                    Optional.of(clienteService.obtenerClientes())));
        }
    }

    @CircuitBreaker(name = "imagenCB", fallbackMethod = "crearClienteCompletoFallBack")
    @PostMapping(value = "/clientecompleto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> crearClienteCompleto(String clienteModel, MultipartFile multipartFile) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED,
                Optional.of(clienteCompletoService.guardarClienteCompleto(clienteModel, multipartFile))));
    }

    @PostMapping()
    public ResponseEntity<Object> guardarCliente(@RequestBody Cliente cliente) throws IOException {
        if (clienteService.obtenerPorNumDoc(cliente.getCliNumDoc()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED,
                    Optional.of(clienteService.guarCliente(cliente))));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                    "No se pudo realizar el registro porque ya existe un cliente con este numero de documento"));
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable("id") Long id) {
        if (clienteService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se " +
                    "encontro a un cliente por este id: " + id));
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND,
                    Optional.of(clienteService.obtenerPorId(id))));
        }
    }

    @GetMapping("/query/mayor")
    public ResponseEntity<Object> obtenerPorEdadMayor(@RequestParam("cliEdaMay") Integer cliedad) {
        if (clienteService.obtenerPorEdadMayor(cliedad).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "Ningun " +
                    "cliente es mayor o igual que " + cliedad));
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND,
                    Optional.of(clienteService.obtenerPorEdadMayor(cliedad))));
        }
    }

    @CircuitBreaker(name = "imagenCB", fallbackMethod = "eliminarPorIdFallBack")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> eliminarPorId(@PathVariable("id") Long id) {
        if (clienteService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "El " +
                    "cliente con el id " + id + " no se encontró"));
        } else {
            clienteService.eliminarCliente(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND, Optional.of("El " +
                    "cliente con el id " + id + " se elimió")));
        }
    }

    @GetMapping("/query")
    public ResponseEntity<Object> obtenerPorNumDocTip(@RequestParam("clinumdoc") String clinumdoc, @RequestParam(
            "clitipdoc") String clitipdoc) {
        if (clienteService.obtenerPorNumDocTip(clinumdoc, clitipdoc).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "El " +
                    "cliente no se encontró"));
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND,
                    Optional.of(clienteService.obtenerPorNumDocTip(clinumdoc, clitipdoc))));
        }
    }

    @GetMapping("/querydos")
    public ResponseEntity<Object> obtenerPorNumDoc(@RequestParam("cliImgNum") String clinumdoc) {
        if (clienteService.obtenerPorNumDoc(clinumdoc).isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new AnswerNotData(HttpStatus.NOT_FOUND, "El " +
                    "cliente no se encontró"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new AnswerData(HttpStatus.FOUND,
                    Optional.of(clienteService.obtenerPorNumDoc(clinumdoc))));
        }
    }

    private ResponseEntity<Object> crearClienteCompletoFallBack(String clienteModel, MultipartFile multipartFile,
                                                                FeignException e) throws IOException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                e.getMessage()));
    }

    private ResponseEntity<Object> eliminarPorIdFallBack(@PathVariable("id") Long id,
                                                         RuntimeException e) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,
                e.getMessage()));
    }

}
