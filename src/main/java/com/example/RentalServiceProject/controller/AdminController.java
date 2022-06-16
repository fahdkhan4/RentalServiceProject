package com.example.RentalServiceProject.controller;
import com.example.RentalServiceProject.dto.*;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getALLUsers(){
        List<User> users = adminService.getAllUser();
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

    @PatchMapping("/assetbookingstatus/{id}")
    public ResponseEntity<AssetBookingDto> getAssetBooking_ForUpdate(@PathVariable Long id,@RequestBody AssetBookingDto assetBookingDto){
        try{
            return ResponseEntity.ok(adminService.updateAssetBooking_Status(id,assetBookingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

    @PatchMapping("/userratingstatus/{id}")
    public ResponseEntity<UserRatingDto> getUserRating_ForUpdate(@PathVariable Long id, @RequestBody UserRatingDto userRatingDto){
        try{
            return ResponseEntity.ok(adminService.updateUserRating_Status(id,userRatingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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


}

// use check box to give status , publish or reject
