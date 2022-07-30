package com.example.RentalServiceProject.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {


    public String getImageByName();
    public void saveImage(MultipartFile image);

}
