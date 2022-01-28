package com.smo.imagen.application.validator;

import com.smo.imagen.domain.answers.AnswerNotData;
import com.smo.imagen.domain.exceptions.ApiImageInvalid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class ValidatorImplementsImagen implements IValidatorImagen{

    @Override
    public void validatorImagen(MultipartFile multipartFile) throws IOException {

        if (multipartFile == null) {
            throw new ApiImageInvalid(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,"El archivo debe de contener una imagen"));
        }

        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            throw new ApiImageInvalid(new AnswerNotData(HttpStatus.NOT_ACCEPTABLE,"El archivo debe de contener una imagen"));
        }


    }
}
