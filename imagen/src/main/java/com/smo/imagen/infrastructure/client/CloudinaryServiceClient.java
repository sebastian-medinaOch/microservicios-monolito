package com.smo.imagen.infrastructure.client;

import com.smo.imagen.domain.answers.AnswerNotData;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "cloudinary-service")
public interface CloudinaryServiceClient {

    @PostMapping(value = "cloudinary/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadImage(MultipartFile multipartFile) throws IOException;

    @DeleteMapping(value = "cloudinary/deletelist")
    public ResponseEntity<Object> deleteImagenList(List<String> list) throws Exception;

    @DeleteMapping(value = "cloudinary/delete/{id}")
    public ResponseEntity<Object> deleteImagen(@PathVariable String id) throws IOException;

}
