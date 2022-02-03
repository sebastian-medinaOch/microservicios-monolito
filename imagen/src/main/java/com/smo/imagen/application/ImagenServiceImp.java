package com.smo.imagen.application;

import com.smo.imagen.application.validator.IValidatorImagen;
import com.smo.imagen.domain.ImagenModel;
import com.smo.imagen.infrastructure.ImagenRepository;
import com.smo.imagen.infrastructure.client.CloudinaryServiceClient;
import org.json.*;
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

    JSONObject jsObject;
    @Autowired
    CloudinaryServiceClient cloudinaryServiceClient;

    @Override
    public List<ImagenModel> obtenerTodosCliImg() {
        return imagenRepository.findAll();
    }

    public ImagenModel guardarClienteImagen(String cliImgNum, MultipartFile multipartFile) throws IOException {
        iValidatorImagen.validatorImagen(multipartFile);
        String cloudinary = cloudinaryServiceClient.uploadImage(multipartFile).getBody().toString();
        String imgUrl = cloudinary.toString().split(
                "data")[1].split("url=")[1].split(",")[0].trim();
        String imgId = cloudinary.split("data")[1].split(
                        "public_id=")[1].split(",")[0].trim();
        ImagenModel imagenModel = new ImagenModel();
        imagenModel.setCliImgNum(cliImgNum.trim());
        imagenModel.setCliImgUrl(imgUrl);
        imagenModel.setCloIdImg(imgId);
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
                if (cloudinaryServiceClient.deleteImagenList(listDelete).getBody().toString().split(",")[1].trim().equals("status=ACCEPTED")) {
                    imagenRepository.deleteClienteImagenModelByCliImgNum(cliimgNum);
                    return true;
                } else {
                    return false;
                }
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
            cloudinaryServiceClient.deleteImagen(cloIdImg);
            return imagenRepository.deleteClienteImagenModelByCloIdImg(cloIdImg);
        } catch (Exception e) {
            return imagenRepository.deleteClienteImagenModelByCloIdImg(cloIdImg);
        }
    }
}