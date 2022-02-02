package com.smo.cloudinary.application;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public interface ICloudinary {
    public Map<?,?> upload(MultipartFile multipartFile) throws IOException;
    public Map<?,?> delete(String id) throws IOException;
    public File convert(MultipartFile multipartFile) throws IOException;
    public Boolean deleteList(List<String> list) throws IOException, Exception;
}
