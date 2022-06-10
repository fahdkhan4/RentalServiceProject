package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.AssetBookingDto;
import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.AssetBooking;
import com.example.RentalServiceProject.service.AssetBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssetBookingController {

    @Autowired
    AssetBookingService assetBookingService;

    @GetMapping("/assetbooking")
    public ResponseEntity<List<AssetBooking>> getAllAssetbooking(){
        List<AssetBooking> assetbooking = assetBookingService.getAllAssetBooking();
        if(!assetbooking.isEmpty()){
            return ResponseEntity.ok(assetbooking);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/assetbooking")
    public ResponseEntity<AssetBookingDto> addAssetbooking(@RequestBody AssetBookingDto assetbookingDto){
        try{
            return ResponseEntity.ok(assetBookingService.addAssetBooking_In_db(assetbookingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/assetbooking/{id}")
    public ResponseEntity<Optional<AssetBooking>> getAssetbooking_By_Id(@PathVariable Long id){
        Optional<AssetBooking> asset = assetBookingService.getAssetBooking_ById(id);
        if(asset.isPresent()){
            return  ResponseEntity.ok(asset);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/assetbooking/{id}")
    public ResponseEntity<Optional<AssetBookingDto>> updateAssetbooking_By_Id(@PathVariable Long id,@RequestBody AssetBookingDto assetDto){
        try{
            return ResponseEntity.ok(assetBookingService.updateAssetBooking_byId(id,assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/assetbooking/{id}")
    public ResponseEntity<Void> deleteAssetbooking_By_Id(@PathVariable Long id){
        try{
            assetBookingService.deleteAssetBooking_byId(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
