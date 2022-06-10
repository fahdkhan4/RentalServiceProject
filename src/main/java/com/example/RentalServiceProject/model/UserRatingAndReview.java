package com.example.RentalServiceProject.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserRatingAndReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRatingAndReviewId;
    @ManyToOne
    private User user;
    @ManyToOne
    private User provider;
    private Double rating;
    private String review;
    private String status;

}
