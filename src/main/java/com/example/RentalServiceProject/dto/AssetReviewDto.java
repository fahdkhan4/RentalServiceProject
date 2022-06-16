package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.User;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetReviewDto {

    private Long id;
    private Double rating;
    private String review;
    private InitialStatus status = InitialStatus.in_review;
    private Asset asset;
    private User user;
}
