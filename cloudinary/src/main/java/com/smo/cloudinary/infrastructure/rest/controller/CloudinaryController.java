package com.smo.cloudinary.infrastructure.rest.controller;

import com.smo.cloudinary.application.ICloudinary;
import com.smo.cloudinary.application.validator.IValidatorImagen;
import com.smo.cloudinary.domain.answers.AnswerData;
import com.smo.cloudinary.domain.answers.AnswerNotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cloudinary")
public class CloudinaryController {

    @Autowired
    ICloudinary cloudinary;

    @Autowired
    IValidatorImagen validatorImagen;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadImage(MultipartFile multipartFile) throws IOException{
        validatorImagen.validatorImagen(multipartFile);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new AnswerData(HttpStatus.ACCEPTED, Optional.of(cloudinary.upload(multipartFile))));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteImagen(@PathVariable String id) throws IOException {
        Map<?,?> result = this.cloudinary.delete(id);
        if (result.get("result").equals("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AnswerNotData(HttpStatus.NOT_FOUND, "Imagen no encontrada"));
        } else{
            return ResponseEntity.ok(new AnswerNotData(HttpStatus.ACCEPTED, "Imagen eliminada "));
        }
    }

}
