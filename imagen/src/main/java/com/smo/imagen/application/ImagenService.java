package com.smo.imagen.application;

import com.smo.imagen.domain.ImagenModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public interface ImagenService {

    public List<ImagenModel> obtenerTodosCliImg();
    public ImagenModel guardarClienteImagen(String cliImgNum, MultipartFile multipartFile) throws IOException;
    public List<ImagenModel> findByCliImgNum(String cliimgnum);
    public Boolean eliminarCliImg(String cliimgNum);
    public Long eliminarCliImgUnica(String cloIdImg);


}
