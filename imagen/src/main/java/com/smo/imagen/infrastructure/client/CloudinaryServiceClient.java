package com.smo.imagen.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "cloudinary-service")
public interface CloudinaryServiceClient {

    @PostMapping("cloudinary/save")
    public ResponseEntity<Object> uploadImage(MultipartFile multipartFile) throws IOException;
}
