package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.RequestOfServiceDto;
import com.example.RentalServiceProject.model.RequestOfService;
import com.example.RentalServiceProject.service.RequestOfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RequestOfServiceController {

    @Autowired
    RequestOfServiceService requestOfServiceService;

    @GetMapping("/requestofservice")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<List<RequestOfService>> getRequestOfServiceByStatus(){
        return ResponseEntity.ok(requestOfServiceService.getRequestOfServiceByStatus());
    }

    @PostMapping("/requestofservice")
    @PreAuthorize("hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<RequestOfServiceDto> addRequestOfService(@RequestBody RequestOfServiceDto requestOfServiceDto){
        try{
            return ResponseEntity.ok(requestOfServiceService.addRequestOfService_In_db(requestOfServiceDto));
        }catch (Exception e){
            System.out.println("Error in Adding Request Of Service Error :"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/requestofservice/{id}")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<Optional<RequestOfService>> getRequestOfService_By_Id(@PathVariable Long id){
        return ResponseEntity.ok(requestOfServiceService.getRequestOfService_ById(id));
    }

    @PutMapping("/requestofservice/{id}")
    @PreAuthorize("hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<RequestOfServiceDto> updateRequestOfService_By_Id(@PathVariable Long id,@RequestBody RequestOfServiceDto assetDto){
        try{
            return ResponseEntity.ok(requestOfServiceService.updateRequestOfService_byId(id,assetDto));
        }catch (Exception e){
            System.out.println("Error in updating request of Service "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/requestofservice/{id}")
    @PreAuthorize("hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteRequestOfService_By_Id(@PathVariable Long id){
        try{
            requestOfServiceService.deleteRequestOfService_byId(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            System.out.println(e);
            throw  new ContentNotFoundException("Cannot Delete! No request found having id "+id);
        }
    }



}
