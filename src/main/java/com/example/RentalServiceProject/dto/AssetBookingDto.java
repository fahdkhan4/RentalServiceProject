package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.User;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AssetBookingDto {

    private Long Id;
    private Asset asset;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private InitialStatus status = InitialStatus.in_review;

}
