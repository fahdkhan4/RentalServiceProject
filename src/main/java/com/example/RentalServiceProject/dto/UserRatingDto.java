package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.User;
import lombok.*;




@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRatingDto {


    private Long userRatingAndReviewId;
    private User user;
    private User provider;
    private String status;
    private Double rating;
    private String review;

}
