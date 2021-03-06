package com.smo.imagen.infrastructure.rest.controller;

import com.smo.imagen.application.ImagenService;
import com.smo.imagen.domain.answers.AnswerData;
import com.smo.imagen.domain.answers.AnswerNotData;
import com.smo.imagen.infrastructure.ImagenRepository;
import com.smo.imagen.infrastructure.client.ClientServiceClient;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/imagenes")
@RequiredArgsConstructor
public class ImagenController {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ImagenService imagenService;

    @Autowired
    ClientServiceClient clientServiceClient;

    @CircuitBreaker(name = "clienteCB", fallbackMethod = "guardarClienteImagenMongoFallback")
    @PostMapping("/crear")
    public ResponseEntity<Object> guardarClienteImagenMongo(String cliImgNum, MultipartFile multipartFile) throws IOException {
        if (clientServiceClient.obtenerPorNumDoc(cliImgNum).getBody().toString().split(",")[1].trim().equals("status" + "=FOUND")) {
            return ResponseEntity.status(HttpStatus.OK).body(new AnswerData(HttpStatus.ACCEPTED,
                    Optional.of(imagenService.guardarClienteImagen(cliImgNum, multipartFile))));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new AnswerNotData(HttpStatus.NOT_FOUND, "No se " +
                    "encontrĂ³ a un cliente por este numero de documento " + cliImgNum + " , para poder crear " +
                    "imagenes, por favor crear un cliente"));
        }
    }

    @GetMapping("/obtenertodos")
    public ResponseEntity<Object> obtenerTodosImg() {
        if (imagenService.obtenerTodosCliImg().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND,
                    "No se " + "encontraron clientes"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new AnswerData(HttpStatus.OK,
                    Optional.of(imagenService.obtenerTodosCliImg())));
        }
    }


    @GetMapping("/obtenerimagen/{cliimgnum}")
    public ResponseEntity<Object> findByCliImgNum(@PathVariable String cliimgnum) throws IOException {
        if (imagenService.findByCliImgNum(cliimgnum).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND,
                    "No se " + "encontrĂ³ imagenes con el numero de docuemento: " + cliimgnum));
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(new AnswerData(HttpStatus.FOUND,
                    Optional.of(imagenService.findByCliImgNum(cliimgnum))));
        }
    }

    @CircuitBreaker(name = "cloudinaryCB", fallbackMethod = "eliminarImgFallback")
    @DeleteMapping(path = "/eliminarimg/{cliimgnum}")
    public ResponseEntity<Object> eliminarImg(@PathVariable("cliimgnum") String cliimgnum) throws IOException {
        if (imagenService.eliminarCliImg(cliimgnum)) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED, Optional.of(
                    "Al cliente con el numero de documento " + cliimgnum + " se le eliminaron todas las imagenes.")));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE, "Al cliente"
                    + " con el numero de documento " + cliimgnum + " no se le eliminaron imagenes."));
        }
    }

    @CircuitBreaker(name = "cloudinaryCB", fallbackMethod = "eliminarCliImgUnicaFallback")
    @DeleteMapping("/eliminarimg/imagen/{cloIdImg}")
    public ResponseEntity<Object> eliminarImgUnica(@PathVariable("cloIdImg") String cloIdImg) throws IOException {
        if (imagenService.eliminarCliImgUnica(cloIdImg).toString().equals("0")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND,
                    "No se " + "encontrĂ³ ninguna imagen con el id: " + cloIdImg));
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED, Optional.of(
                    "Se eliminĂ³ correctamente la imagen con el id: " + cloIdImg)));
        }
    }

    public ResponseEntity<Object> guardarClienteImagenMongoFallback(String cliImgNum, MultipartFile multipartFile,
                                                                    FeignException e) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(new AnswerNotData(HttpStatus.NOT_FOUND,
                e.getMessage()));
    }

    public ResponseEntity<Object> eliminarImgFallback(@PathVariable("cliimgnum") String cliimgnum, FeignException e) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(new AnswerNotData(HttpStatus.NOT_FOUND,
                e.getMessage()));
    }

    public ResponseEntity<Object> eliminarCliImgUnicaFallback(@PathVariable("cloIdImg") String cloIdImg,
                                                              FeignException e) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(new AnswerNotData(HttpStatus.NOT_FOUND,
                e.getMessage()));
    }

}
