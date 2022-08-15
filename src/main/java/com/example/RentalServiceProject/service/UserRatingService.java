package com.example.RentalServiceProject.service;
import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.UserRatingDto;
import com.example.RentalServiceProject.model.UserRatingAndReview;
import com.example.RentalServiceProject.repo.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserRatingService {

    @Autowired
    UserRatingRepository userRatingRepository;
    @Autowired
    UserService userService;

//                                                                                          Adding user Rating and reviews in database
    public UserRatingDto addingUserRatingAndReviews(UserRatingDto userRatingDto) {
        User user = userService.getUsersbyStatus().stream().filter(user1 -> user1.getId().equals(userRatingDto.getUser().getId())).findAny().get();
        User serviceProvider = userService.getUsersbyStatus().stream().filter(user1 -> user1.getId().equals(userRatingDto.getProvider().getId())).findAny().get();
        userRatingDto.setUser(user);
        userRatingDto.setProvider(serviceProvider);

        return todto(userRatingRepository.save(dto(userRatingDto)));
    }

    public List<UserRatingAndReview> getAllUserRatingAndReviews() {
        return userRatingRepository.findAll();
    }

    public List<UserRatingAndReview> getUserRatingAndReviewsByStatus() {
        List<UserRatingAndReview> userRatingAndReviews = userRatingRepository.findByStatus(InitialStatus.Published);
        if(!userRatingAndReviews.isEmpty()){
            return userRatingAndReviews;
        }
       throw new ContentNotFoundException("No User Rating And Reviews exist");
    }

    public Optional<UserRatingAndReview> getUserRatingAndReviewsById(Long id) {
        Optional<UserRatingAndReview> userRatingAndReview = userRatingRepository.findById(id);
        if(userRatingAndReview.isPresent()){
            return userRatingAndReview;
        }
        throw new ContentNotFoundException("No User Review And Rating exist with id "+id);
    }

    public void deleteUserRatingAndReviews(Long id) {
        userRatingRepository.deleteById(id);
    }

    public Optional<UserRatingDto> updateUserRatingAndReviewsById(Long id, UserRatingDto userRatingDto) {
        UserRatingAndReview userRating = getAllUserRatingAndReviews().stream().filter(el->el.getUserRatingAndReviewId().equals(id)).findAny().get();
        if(userRating != null){
//            userRating.setUser(userRatingDto.getUser());
            userRating.setRating(userRatingDto.getRating());
            userRating.setReview(userRatingDto.getReview());
//            userRating.setProvider(userRatingDto.getProvider());
        }
        return  Optional.of(todto(userRatingRepository.save(userRating)));
    }

    public UserRatingAndReview dto(UserRatingDto userRatingDto){
        return UserRatingAndReview.builder().user(userRatingDto.getUser()).userRatingAndReviewId(userRatingDto.getUserRatingAndReviewId())
                .status(userRatingDto.getStatus()).provider(userRatingDto.getProvider()).rating(userRatingDto.getRating()).review(userRatingDto.getReview()).build();
    }

    public UserRatingDto todto(UserRatingAndReview userRatingAndReview){
        return  UserRatingDto.builder().user(userRatingAndReview.getUser()).userRatingAndReviewId(userRatingAndReview.getUserRatingAndReviewId())
                .status(userRatingAndReview.getStatus()).provider(userRatingAndReview.getProvider()).rating(userRatingAndReview.getRating()).review(userRatingAndReview.getReview()).build();
    }


}
