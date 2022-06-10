package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.UserRatingDto;
import com.example.RentalServiceProject.model.UserRatingAndReview;
import com.example.RentalServiceProject.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRatingController {

    @Autowired
    UserRatingService userRatingService;

    @PostMapping("/userrating")
    public ResponseEntity<UserRatingDto> add_UserRatingAndReviews(@RequestBody UserRatingDto userRatingDto){
        try {
            Optional<UserRatingDto> userRating= Optional.of(userRatingService.addingUserRatingAndReviews(userRatingDto));
            return ResponseEntity.of(userRating);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/userrating")
    public ResponseEntity<List<UserRatingAndReview>> get_UserRatingAndReviews(){
        Optional<List<UserRatingAndReview>> getUsersRating = Optional.ofNullable(userRatingService.getAllUserRatingAndReviews());
        if(getUsersRating.get().size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(getUsersRating );
    }

    @GetMapping("/userrating/{id}")
    public ResponseEntity<Optional<UserRatingAndReview>> get_UserRatingAndReviews_by_Id(@PathVariable Long id){
        Optional<UserRatingAndReview> getbyid = userRatingService.getUserRatingAndReviewsById(id);
        if(getbyid.isPresent()){
            return ResponseEntity.ok(getbyid);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/userrating/{id}")
    public  ResponseEntity<Void> delete_UserRatingAndReviews_by_id(@PathVariable Long id){
        try{
            userRatingService.deleteUserRatingAndReviews(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/userrating/{id}")
    public ResponseEntity<Optional<UserRatingDto>> update_UserRatingAndReviews_By_Id(@PathVariable Long id,@RequestBody UserRatingDto userRatingDto){
        try{
            return ResponseEntity.ok(userRatingService.updateUserRatingAndReviewsById(id,userRatingDto));
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
