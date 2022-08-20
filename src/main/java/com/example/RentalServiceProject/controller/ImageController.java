package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.service.AssetService;
import com.example.RentalServiceProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/api")
public class ImageController {

      @Autowired
      UserService userService;
      @Autowired
      AssetService assetService;
//                                                                  Api to get User Image
        @GetMapping("/image/userimages/{fileName}")
//        @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('SERVICE_PROVIDER')")
        public void getUserImage(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
            InputStream inputStream = userService.getImageByName(fileName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStream,response.getOutputStream());
        }

//                                                                  Api to get Asset Image
        @GetMapping("/image/assetimages/{fileName}")
        @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('SERVICE_PROVIDER')")
        public void getAssetImage(@PathVariable("fileName") String fileName,HttpServletResponse response) throws IOException {
            InputStream inputStream = assetService.getImageByName(fileName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(inputStream,response.getOutputStream());
        }

}
