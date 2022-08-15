package com.example.RentalServiceProject.controller;
import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.dto.UserRatingDto;
import com.example.RentalServiceProject.model.UserRatingAndReview;
import com.example.RentalServiceProject.service.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserRatingController {

    @Autowired
    UserRatingService userRatingService;
//                                                                                  adding user rating and review in Database
    @PostMapping("/userrating")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('SERVICE_PROVIDER')")
    public ResponseEntity<UserRatingDto> add_UserRatingAndReviews(@RequestBody UserRatingDto userRatingDto){
        try {
            UserRatingDto userRating= userRatingService.addingUserRatingAndReviews(userRatingDto);
            return ResponseEntity.ok(userRating);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/userrating")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<List<UserRatingAndReview>> get_UserRatingAndReviewsByStatus(){
       List<UserRatingAndReview> getUsersRating = userRatingService.getUserRatingAndReviewsByStatus();
        return ResponseEntity.ok(getUsersRating);
    }

    @GetMapping("/userrating/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public ResponseEntity<Optional<UserRatingAndReview>> get_UserRatingAndReviews_by_Id(@PathVariable Long id){
        return  ResponseEntity.ok(userRatingService.getUserRatingAndReviewsById(id));
    }

    @DeleteMapping("/userrating/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('SERVICE_PROVIDER') or hasRole('ADMIN')")
    public  ResponseEntity<Void> delete_UserRatingAndReviews_by_id(@PathVariable Long id){
        try{
            userRatingService.deleteUserRatingAndReviews(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception e){
            System.out.println(e);
            throw new ContentNotFoundException("Cannot Delete! User Rating and Review not exist having id "+id);
        }
    }

    @PutMapping("/userrating/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('SERVICE_PROVIDER')")
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
