package com.smo.cloudinary.application.validator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface IValidatorImagen {

    void validatorImagen(MultipartFile multipartFile) throws IOException;

}
