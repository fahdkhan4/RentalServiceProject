package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.model.Asset;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

public interface ImageStorage {


    public InputStream getImageByName(String image) throws FileNotFoundException;
    public void saveImage(MultipartFile image);
}
