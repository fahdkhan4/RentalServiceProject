package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.AssetReview;
import com.example.RentalServiceProject.model.User;
import lombok.*;


import javax.persistence.Enumerated;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetDto {

    private Long Id;
    private String name;
    private Double pricePerDay;
    private String location;
    private String type;
    private InitialStatus status = InitialStatus.in_review;
    private User user;
//    private List<AssetReview> assetReview;

}
