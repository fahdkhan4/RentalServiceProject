package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.AssetImages;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
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
    private String image;
    private String location;
    private String startDate;
    private String endDate;
    private String city;
    private String type;
    private InitialStatus status = InitialStatus.in_review;
    private User user;



}
