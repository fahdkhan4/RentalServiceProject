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
    @NotEmpty
    private Asset asset;
    @NotEmpty
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    @PositiveOrZero
    private Double price;
    private InitialStatus status = InitialStatus.in_review;

}
