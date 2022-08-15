package com.example.RentalServiceProject.controller;
import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.*;
import com.example.RentalServiceProject.model.*;
import com.example.RentalServiceProject.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
//                                                                                  Constructor injection
    private final AdminService adminService;

    AdminController(AdminService adminService1){
        this.adminService = adminService1;
    }
//                                                                                  Saving Roles of Users in Database
    @PostMapping("/roles")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Roles> addRoles(@RequestBody Roles roles){
        return ResponseEntity.ok(adminService.addRolesInDb(roles));
    }
//                                                                              ///// Admin User Operations
    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getALLUsers(){
        List<User> users = adminService.getAllUsers();
        if(!users.isEmpty()){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUser_ForUpdate(@PathVariable Long id,@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(adminService.updateUser_Status(id,userDto));
        }
        catch (Exception e){
            System.out.println(e);
            throw new ContentNotFoundException("User with id"+id+" not present");
        }
    }
//                                                                              ///// Admin Asset Operations
    @GetMapping("/asset")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Asset>> getALLAsset(){
        List<Asset> assets = adminService.getAllAssets();
        if(!assets.isEmpty()){
            return ResponseEntity.ok(assets);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/assetstatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AssetDto> getAsset_ForUpdate(@PathVariable Long id, @RequestBody AssetDto assetDto){
        try{
            return ResponseEntity.ok(adminService.updateAsset_Status(id,assetDto));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //                                                                              ///// Admin Asset Booking Operations
    @GetMapping("/assetbooking")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AssetBooking>> getALLAssetBooking(){
        List<AssetBooking> allAssetBooking = adminService.getAllAssetBooking();
        if(!allAssetBooking.isEmpty()){
            return ResponseEntity.ok(allAssetBooking);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/assetbookingstatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AssetBookingDto> getAssetBooking_ForUpdate(@PathVariable Long id,@RequestBody AssetBookingDto assetBookingDto){
        try{
            return ResponseEntity.ok(adminService.updateAssetBooking_Status(id,assetBookingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //                                                                              ///// Admin Asset Review Operations
    @GetMapping("/assetreview")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AssetReview>> getALLAssetReview(){
        List<AssetReview> allAssetReview = adminService.getAllAssetReview();
        if(!allAssetReview.isEmpty()){
            return ResponseEntity.ok(allAssetReview);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/assetreviewstatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AssetReviewDto> getAssetReview_ForUpdate(@PathVariable Long id, @RequestBody AssetReviewDto assetReviewDto){
        try{
            return ResponseEntity.ok(adminService.updateAssetReview_Status(id,assetReviewDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //                                                                              ///// Admin User Rating Operations
    @GetMapping("/userrating")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserRatingAndReview>> getALLUserRatingAndReview(){
        List<UserRatingAndReview> allUserRatingAndReview = adminService.getAllUserRatingAndReview();
        if(!allUserRatingAndReview.isEmpty()){
            return ResponseEntity.ok(allUserRatingAndReview);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/userratingstatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserRatingDto> getUserRating_ForUpdate(@PathVariable Long id, @RequestBody UserRatingDto userRatingDto){
        try{
            return ResponseEntity.ok(adminService.updateUserRating_Status(id,userRatingDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //                                                                              ///// Admin Request Of Service Operations
    @GetMapping("/requestofservice")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<RequestOfService>> getALLRequestOfService(){
        List<RequestOfService> allRequestOfService = adminService.getAllRequestOfService();
        if(!allRequestOfService.isEmpty()){
            return ResponseEntity.ok(allRequestOfService);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/requeststatus/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
