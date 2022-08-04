package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
//                                  image of the asset from assetimage table to send response
    private String image;

}
