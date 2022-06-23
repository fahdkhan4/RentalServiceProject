package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if(!assets.isEmpty()){
            return ResponseEntity.ok(assets);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/asset")
    public ResponseEntity<AssetDto> addAsset(@RequestBody AssetDto assetDto){
        System.out.println(assetDto);
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
        if(asset.isPresent()){
            return  ResponseEntity.ok(asset);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/asset/{id}")
    public ResponseEntity<AssetDto> updateAsset_By_Id(@PathVariable Long id,@RequestBody AssetDto assetDto){
        try{
            return ResponseEntity.ok(assetService.updateAsset_byId(id,assetDto));
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
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/asset/search")
    public ResponseEntity<List<AssetDto>> filterAsset(@RequestBody SearchCriteria search){
         return ResponseEntity.ok(assetService.search(search));
    }

}
