package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.User;
import lombok.*;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
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
    private String startDate;
    private String endDate;
    private Double price;
    private InitialStatus status = InitialStatus.in_review;

}
