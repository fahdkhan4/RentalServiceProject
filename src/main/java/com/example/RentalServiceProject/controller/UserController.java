package com.example.RentalServiceProject.controller;
import com.example.RentalServiceProject.config.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/user")
    public ResponseEntity<UserDto> add_User(@Valid @RequestBody UserDto userDto){
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
        List<User> getUsers = userService.getUsersbyStatus();
        return ResponseEntity.ok(getUsers);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> get_Users_by_Id(@PathVariable Long id){
        Optional<User> getbyid = userService.getUserById(id);
        return ResponseEntity.ok(getbyid);
    }

    @DeleteMapping("/user/{id}")
    public  ResponseEntity<Void> delete_User_by_id(@PathVariable Long id){

        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e){
            throw new ContentNotFoundException("No User present with id of "+id);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> update_User_By_Id(@PathVariable Long id,@Valid @RequestBody UserDto userDto){
        try {
             return ResponseEntity.ok(userService.updateUserById(id,userDto));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/search")
    public ResponseEntity<List<UserDto>> filterUser(@RequestBody SearchCriteria searchCriteria){
        List<UserDto> filteredUser = userService.filteringUser(searchCriteria);
        if(!filteredUser.isEmpty()){
            return ResponseEntity.ok(filteredUser);
        }
        return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
    }


}
