package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.AssetReviewDto;
import com.example.RentalServiceProject.model.AssetReview;
import com.example.RentalServiceProject.service.AssetReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssetReviewController {

    @Autowired
    AssetReviewService assetReviewService;

    @GetMapping("/assetreview")
    public ResponseEntity<List<AssetReview>> getAllAssetReview(){
        List<AssetReview> assetReviews = assetReviewService.getAssetReviewByStatus();
        if(!assetReviews.isEmpty()){
            return ResponseEntity.ok(assetReviews);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/assetreview")
    public ResponseEntity<AssetReviewDto> addAssetReview(@RequestBody AssetReviewDto assetReviewDto){
        try{
            return ResponseEntity.ok(assetReviewService.addAssetReview_In_db(assetReviewDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/assetreview/{id}")
    public ResponseEntity<Optional<AssetReview>> getAssetReview_By_Id(@PathVariable Long id){
        Optional<AssetReview> assetReview = assetReviewService.getAssetReview_ById(id);
        if(assetReview.isPresent()){
            return  ResponseEntity.ok(assetReview);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/assetreview/{id}")
    public ResponseEntity<Optional<AssetReviewDto>> updateAssetReview_By_Id(@PathVariable Long id,@RequestBody AssetReviewDto assetDto){
        try{
            return ResponseEntity.ok(assetReviewService.updateAssetReview_byId(id,assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/assetreview/{id}")
    public ResponseEntity<Void> deleteAssetReview_By_Id(@PathVariable Long id){
        try{
            assetReviewService.deleteAssetReview_byId(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
