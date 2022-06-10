package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.RequestOfServiceDto;
import com.example.RentalServiceProject.model.RequestOfService;
import com.example.RentalServiceProject.service.RequestOfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RequestOfServiceController {

    @Autowired
    RequestOfServiceService requestOfServiceService;

    @GetMapping("/requestofservice")
    public ResponseEntity<List<RequestOfService>> getAllRequestOfService(){
        List<RequestOfService> requestOfService = requestOfServiceService.getAllRequestOfService();
        if(!requestOfService.isEmpty()){
            return ResponseEntity.ok(requestOfService);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/requestofservice")
    public ResponseEntity<RequestOfServiceDto> addRequestOfService(@RequestBody RequestOfServiceDto requestOfServiceDto){
        try{
            return ResponseEntity.ok(requestOfServiceService.addRequestOfService_In_db(requestOfServiceDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/requestofservice/{id}")
    public ResponseEntity<Optional<RequestOfService>> getRequestOfService_By_Id(@PathVariable Long id){
        Optional<RequestOfService> requestofservice = requestOfServiceService.getRequestOfService_ById(id);
        if(requestofservice.isPresent()){
            return  ResponseEntity.ok(requestofservice);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/requestofservice/{id}")
    public ResponseEntity<Optional<RequestOfServiceDto>> updateRequestOfService_By_Id(@PathVariable Long id,@RequestBody RequestOfServiceDto assetDto){
        try{
            return ResponseEntity.ok(requestOfServiceService.updateRequestOfService_byId(id,assetDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/requestofservice/{id}")
    public ResponseEntity<Void> deleteRequestOfService_By_Id(@PathVariable Long id){
        try{
            requestOfServiceService.deleteRequestOfService_byId(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
