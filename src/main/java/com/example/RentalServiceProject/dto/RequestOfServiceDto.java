package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.User;
import lombok.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequestOfServiceDto {

    private Long id;
    private String type;
    private String details;
    private InitialStatus status = InitialStatus.in_review;
    private User user;

}
