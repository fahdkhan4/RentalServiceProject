package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> add_User(@RequestBody UserDto userDto){
          try {
              Optional<UserDto> postuser= Optional.of(userService.addingUser(userDto));
              return ResponseEntity.of(postuser);
          }
          catch (Exception e){
              System.out.println(e);
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> get_Users(){
        Optional<List<User>> getUsers = Optional.ofNullable(userService.getAllUsers());
        if(getUsers.get().size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(getUsers);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> get_Users_by_Id(@PathVariable Long id){
        Optional<User> getbyid = userService.getUserById(id);
        if(getbyid.isPresent()){
                return ResponseEntity.ok(getbyid);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/user/{id}")
    public  ResponseEntity<Void> delete_User_by_id(@PathVariable Long id){
       try{
               userService.deleteUser(id);
               return ResponseEntity.status(HttpStatus.ACCEPTED).build();
       }
       catch (Exception e){
           System.out.println(e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Optional<UserDto>> update_User_By_Id(@PathVariable Long id,@RequestBody UserDto userDto){
        try{
             return ResponseEntity.ok(userService.updateUserById(id,userDto));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    @PatchMapping("/user/{id}")
//    public
}
