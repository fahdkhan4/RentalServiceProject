package com.example.RentalServiceProject.controller;
import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

//                                                                          get user
    @GetMapping("/user")
    public ResponseEntity<List<User>> get_Users(){
        List<User> getUsers = userService.getUsersbyStatus();
        return ResponseEntity.ok(getUsers);
    }
//                                                                          get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> get_Users_by_Id(@PathVariable Long id){
        Optional<User> getbyid = userService.getUserById(id);
        return ResponseEntity.ok(getbyid);
    }
//                                                                         Admin can Delete user only
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<Void> delete_User_by_id(@PathVariable Long id){

        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (Exception e){
            throw new ContentNotFoundException("No User present with id of "+id);
        }
    }
//                                                                      Update user
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> update_User_By_Id(@PathVariable Long id,@Valid @RequestBody UserDto userDto){
        try {
            return ResponseEntity.ok(userService.updateUserById(id, userDto));
        }
        catch (Exception e){
            System.out.println(e);
            throw new ContentNotFoundException("User with id "+id+" not exist");
        }
    }
//                                                                      Filter user by properties
    @GetMapping("/user/search")
    public ResponseEntity<List<UserDto>> filterUser(@RequestBody SearchCriteria searchCriteria){
        List<UserDto> filteredUser = userService.filteringUser(searchCriteria);
        if(!filteredUser.isEmpty()){
            return ResponseEntity.ok(filteredUser);
        }
        return ResponseEntity.status((HttpStatus.NOT_FOUND)).build();
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> SignUp_addingUser(@RequestParam("file") MultipartFile image,@RequestParam("data") String userdata,@RequestParam("isServiceProvider") boolean isServiceProvider){
        try{
            if(image.isEmpty()){
//                                                                         throw image error if empty
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
//                                                                    converting String into UserDto Object
            ObjectMapper mapper = new ObjectMapper();
            UserDto userDto = mapper.readValue(userdata,UserDto.class);
//                                                                     Adding user and file in db
            return ResponseEntity.ok(userService.saveUserInDb(userDto,image,isServiceProvider));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}

//