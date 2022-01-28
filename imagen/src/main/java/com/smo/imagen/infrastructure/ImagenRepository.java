package com.smo.imagen.infrastructure;

import com.smo.imagen.domain.ImagenModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends MongoRepository<ImagenModel, String> {

    List<ImagenModel> findByCliImgNum(String cliimgnum);
    Long deleteClienteImagenModelByCliImgNum(String cliimgnum);
    Long deleteClienteImagenModelByCloIdImg(String cloIdImg);



}
