package com.smo.imagen.application;

import com.smo.imagen.application.validator.IValidatorImagen;
import com.smo.imagen.domain.ImagenModel;
import com.smo.imagen.infrastructure.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImagenServiceImp implements ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private IValidatorImagen iValidatorImagen;


    @Override
    public List<ImagenModel> obtenerTodosCliImg() {
        return imagenRepository.findAll();
    }

    @Override
    public ImagenModel guardarClienteImagen(String cliImgNum, MultipartFile multipartFile) throws IOException {
        iValidatorImagen.validatorImagen(multipartFile);

        ImagenModel imagenModel = new ImagenModel();
        imagenModel.setCliImgNum(cliImgNum.trim());
        imagenModel.setCliImgUrl("HOLA URLss");
        imagenModel.setCloIdImg("HOLA IDss");
        return imagenRepository.save(imagenModel);
    }

    @Override
    public List<ImagenModel> findByCliImgNum(String cliimgnum) {
        return imagenRepository.findByCliImgNum(cliimgnum);
    }

    @Override
    public Boolean eliminarCliImg(String cliimgNum) {
        try {
            if (findByCliImgNum(cliimgNum).size() > 0) {

                List<ImagenModel> list = imagenRepository.findByCliImgNum(cliimgNum);
                List<String> listDelete = new ArrayList<>();
                for (ImagenModel cl : list) {
                    listDelete.add(cl.getCloIdImg());
                }
                ;
                imagenRepository.deleteClienteImagenModelByCliImgNum(cliimgNum);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long eliminarCliImgUnica(String cloIdImg) {
        try {
            return imagenRepository.deleteClienteImagenModelByCloIdImg(cloIdImg);
        } catch (Exception e) {
            return imagenRepository.deleteClienteImagenModelByCloIdImg(cloIdImg);
        }
    }
}