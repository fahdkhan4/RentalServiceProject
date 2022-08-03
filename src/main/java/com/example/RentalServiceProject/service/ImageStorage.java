package com.example.RentalServiceProject.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface ImageStorage {


    public InputStream getImageByName(String image) throws FileNotFoundException;
    public void saveImage(MultipartFile image);

}
