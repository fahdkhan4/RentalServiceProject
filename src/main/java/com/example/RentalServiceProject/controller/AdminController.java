package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.dto.UserDto;
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

}

// use check box to give status , publish or reject
