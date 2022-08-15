package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.AssetReviewDto;
import com.example.RentalServiceProject.model.AssetReview;
import com.example.RentalServiceProject.service.AssetReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssetReviewController {

    @Autowired
    AssetReviewService assetReviewService;
//                                                             get assetReview whose status is Published
    @GetMapping("/assetreview")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<List<AssetReview>> getAllAssetReview(){
        return ResponseEntity.ok(assetReviewService.getAssetReviewByStatus());
    }
    //                                                             add Asset Review
    @PostMapping("/assetreview")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<AssetReviewDto> addAssetReview(@RequestBody AssetReviewDto assetReviewDto){
        try{
            return ResponseEntity.ok(assetReviewService.addAssetReview_In_db(assetReviewDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//                                                                get Asset Review By Id
    @GetMapping("/assetreview/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN') or hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<Optional<AssetReview>> getAssetReview_By_Id(@PathVariable Long id){
        Optional<AssetReview> assetReview = assetReviewService.getAssetReview_ById(id);
        if(assetReview.isPresent()){
            return  ResponseEntity.ok(assetReview);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
//                                                                 Update Asset Review By Id
    @PutMapping("/assetreview/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Optional<AssetReviewDto>> updateAssetReview_By_Id(@PathVariable Long id,@RequestBody AssetReviewDto assetDto){
        try{
            return ResponseEntity.ok(assetReviewService.updateAssetReview_byId(id,assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//                                                                  Delete Asset Review
    @DeleteMapping("/assetreview/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
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
