package com.example.RentalServiceProject.controller;
import com.example.RentalServiceProject.dto.*;
import com.example.RentalServiceProject.model.*;
import com.example.RentalServiceProject.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
//                                                              Constructor injection
    private final AdminService adminService;

    AdminController(AdminService adminService1){
        this.adminService = adminService1;
    }
    
    @GetMapping("/user")
    public ResponseEntity<List<User>> getALLUsers(){
        List<User> users = adminService.getAllUsers();
        if(!users.isEmpty()){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/userstatus/{id}")
    public ResponseEntity<UserDto> getUser_ForUpdate(@PathVariable Long id,@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(adminService.updateUser_Status(id,userDto));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/asset")
    public ResponseEntity<List<Asset>> getALLAsset(){
        List<Asset> assets = adminService.getAllAssets();
        if(!assets.isEmpty()){
            return ResponseEntity.ok(assets);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/assetstatus/{id}")
    public ResponseEntity<AssetDto> getUser_ForUpdate(@PathVariable Long id, @RequestBody AssetDto assetDto){
        try{
            return ResponseEntity.ok(adminService.updateAsset_Status(id,assetDto));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/assetbooking")
    public ResponseEntity<List<AssetBooking>> getALLAssetBooking(){
        List<AssetBooking> allAssetBooking = adminService.getAllAssetBooking();
        if(!allAssetBooking.isEmpty()){
            return ResponseEntity.ok(allAssetBooking);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/assetbookingstatus/{id}")
    public ResponseEntity<AssetBookingDto> getAssetBooking_ForUpdate(@PathVariable Long id,@RequestBody AssetBookingDto assetBookingDto){
        try{
            return ResponseEntity.ok(adminService.updateAssetBooking_Status(id,assetBookingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/assetreview")
    public ResponseEntity<List<AssetReview>> getALLAssetReview(){
        List<AssetReview> allAssetReview = adminService.getAllAssetReview();
        if(!allAssetReview.isEmpty()){
            return ResponseEntity.ok(allAssetReview);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/assetreviewstatus/{id}")
    public ResponseEntity<AssetReviewDto> getAssetReview_ForUpdate(@PathVariable Long id, @RequestBody AssetReviewDto assetReviewDto){
        try{
            return ResponseEntity.ok(adminService.updateAssetReview_Status(id,assetReviewDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/userrating")
    public ResponseEntity<List<UserRatingAndReview>> getALLUserRatingAndReview(){
        List<UserRatingAndReview> allUserRatingAndReview = adminService.getAllUserRatingAndReview();
        if(!allUserRatingAndReview.isEmpty()){
            return ResponseEntity.ok(allUserRatingAndReview);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/userratingstatus/{id}")
    public ResponseEntity<UserRatingDto> getUserRating_ForUpdate(@PathVariable Long id, @RequestBody UserRatingDto userRatingDto){
        try{
            return ResponseEntity.ok(adminService.updateUserRating_Status(id,userRatingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/requestofservice")
    public ResponseEntity<List<RequestOfService>> getALLRequestOfService(){
        List<RequestOfService> allRequestOfService = adminService.getAllRequestOfService();
        if(!allRequestOfService.isEmpty()){
            return ResponseEntity.ok(allRequestOfService);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/requeststatus/{id}")
    public ResponseEntity<RequestOfServiceDto> getRequestStatus_ForUpdate(@PathVariable Long id, @RequestBody RequestOfServiceDto requestOfServiceDto){
        try{
            return ResponseEntity.ok(adminService.updateRequestofService_Status(id,requestOfServiceDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


//    Check Stats Of the booking


}

// use check box to give status , publish or reject
