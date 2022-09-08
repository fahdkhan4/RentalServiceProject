package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import lombok.*;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForDate {
    private Long Id;
    private String name;
    private Double pricePerDay;
    private String image;
    private String address;
    private String startDate;
    private String endDate;
    private String city;
    private String type;
    private InitialStatus status = InitialStatus.in_review;
    private User user;
    private String startingPrice;
    private String endingPrice;
}
