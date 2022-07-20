package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.model.enums.InitialStatus;
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
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;


}
