package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.dto.ForDate;
import com.example.RentalServiceProject.dto.ForImage;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.AssetImages;
import com.example.RentalServiceProject.service.AssetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AssetController {

    @Autowired
    AssetService assetService;
//                                                                                     get asset by status Published
    @GetMapping("/asset")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('CUSTOMER') or hasRole('ADMIN') ")
    public ResponseEntity<List<Asset>> getAssetByStatus(@RequestParam(value = "pageNumber") Integer pageNumber,
                                                        @RequestParam(value = "pageSize") Integer pageSize){
        List<Asset> assets = assetService.getAssetByStatus(pageNumber, pageSize);
        return ResponseEntity.ok(assets);
    }
//                                                                                      get asset by id
    @GetMapping("/asset/cities")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('CUSTOMER') or hasRole('ADMIN') ")
    public ResponseEntity<List<String>> getAssetCities(){
        List<String> cities = assetService.getAssetCities();
        return  ResponseEntity.ok(cities);
    }

    @GetMapping("/asset/{id}")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('CUSTOMER') or hasRole('ADMIN') ")
    public ResponseEntity<Optional<Asset>> getAsset_By_Id(@PathVariable Long id){
        Optional<Asset> asset = assetService.getUser_ById(id);
        return  ResponseEntity.ok(asset);
    }
//                                                                                      Update Asset
    @PutMapping("/asset/{id}")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') ")
    public ResponseEntity<AssetDto> updateAsset_By_Id(@PathVariable Long id,@RequestBody AssetDto assetDto){
        try {
            return ResponseEntity.ok(assetService.updateAsset_byId(id, assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//                                                                                      Delete Asset
    @DeleteMapping("/asset/{id}")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAsset_By_Id(@PathVariable Long id){
        try{
            assetService.deleteAsset_byId(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            throw new ContentNotFoundException("Cannot Delete !! No Asset Found with id "+id);
        }
    }
                                                            // get asset by criteria
    @PostMapping("/asset/criteria")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<List<AssetDto>> getAssetByCriteria(@RequestBody ForDate object,
                                                       @RequestParam(value = "pageNumber") Integer pageNumber,
                                                       @RequestParam(value = "pageSize") Integer pageSize){

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");

            LocalDate startDate = LocalDate.parse(object.getStartDate(), formatter);
            LocalDate endDate = LocalDate.parse(object.getEndDate(), formatter);
            AssetDto assetDto = new AssetDto();
            assetDto.setCity(object.getCity());
            assetDto.setStartDate(startDate);
            assetDto.setEndDate(endDate);
            assetDto.setStartingPrice(Double.parseDouble(object.getStartingPrice().toString()));
            assetDto.setEndingPrice(Double.parseDouble(object.getEndingPrice().toString()));
            System.out.println(assetDto);

            return ResponseEntity.ok(assetService.getAssetByCriteria(assetDto, pageNumber, pageSize));
        }
        catch (Exception e){
            throw new ContentNotFoundException("Cannot Delete !! No Asset Found with id ");
        }
    }
//                                                                                      Filter Asset
    @GetMapping("/asset/search")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('CUSTOMER') or hasRole('ADMIN') ")
    public ResponseEntity<List<AssetDto>> filterAsset(@RequestBody SearchCriteria search){
         return ResponseEntity.ok(assetService.search(search));
    }
//                                                                                      Add Asset Booking in Database
    @PostMapping("/asset")
    @PreAuthorize("hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<AssetDto>  addAsset(@RequestParam("image") MultipartFile image,@RequestParam("data") String assetDetails){
        try {
            if(image.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
             }
            ObjectMapper mapper = new ObjectMapper();
            AssetDto assetDto = mapper.readValue(assetDetails,AssetDto.class);

            return  ResponseEntity.ok(assetService.addAsset_InDb(assetDto,image));
        }
        catch (Exception e){
            System.out.println(e);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//                                                                                      Add asset image
    @PostMapping("/asset/assetimage")
    @PreAuthorize("hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<AssetImages> uploadAssetImageMapper(@RequestParam("data") String froImage, @RequestParam("image") MultipartFile image){

        try{
            ObjectMapper mapper = new ObjectMapper();
            ForImage id = mapper.readValue(froImage, ForImage.class);
            System.out.println(id);
            return ResponseEntity.ok(assetService.uploadAssetImage(id.getId(),image));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
