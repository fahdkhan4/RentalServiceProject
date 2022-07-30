package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.config.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/asset")
    public ResponseEntity<List<Asset>> getAssetByStatus(){
        List<Asset> assets = assetService.getAssetByStatus();
        return ResponseEntity.ok(assets);
    }

    @PostMapping("/asset")
    public ResponseEntity<AssetDto> addAsset(@RequestBody AssetDto assetDto){
        try{
            return ResponseEntity.ok(assetService.addAsset_InDb(assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/asset/{id}")
    public ResponseEntity<Optional<Asset>> getAsset_By_Id(@PathVariable Long id){
        Optional<Asset> asset = assetService.getUser_ById(id);
        return  ResponseEntity.ok(asset);
    }

    @PutMapping("/asset/{id}")
    public ResponseEntity<AssetDto> updateAsset_By_Id(@PathVariable Long id,@RequestBody AssetDto assetDto){
        try {
            return ResponseEntity.ok(assetService.updateAsset_byId(id, assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/asset/{id}")
    public ResponseEntity<Void> deleteAsset_By_Id(@PathVariable Long id){
        try{
            assetService.deleteAsset_byId(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            throw new ContentNotFoundException("Cannot Delete !! No Asset Found with id "+id);
        }
    }

    @GetMapping("/asset/search")
    public ResponseEntity<List<AssetDto>> filterAsset(@RequestBody SearchCriteria search){
         return ResponseEntity.ok(assetService.search(search));
    }

    @PostMapping("/assetimage")
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile image){
        try {
            if(image.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            assetService.saveImage(image);

            return ResponseEntity.ok("Image Saved");
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
