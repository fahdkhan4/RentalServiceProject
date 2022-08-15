package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.enums.InitialStatus;
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
    private InitialStatus status = InitialStatus.in_review;
    private Double rating;
    private String review;

}
