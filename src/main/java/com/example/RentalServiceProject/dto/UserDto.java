package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.InitialStatus;
import lombok.*;

import javax.validation.constraints.*;


@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserDto {
    private Long Id;
    private String name;
    private String number;
    private String email;
    private String type;
    private String cnic;
    private InitialStatus status = InitialStatus.in_review;
}
