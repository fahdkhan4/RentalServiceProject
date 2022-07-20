package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class UserDto {

    private Long Id;
    @NotEmpty(message = "Name Should not be empty")
    private String name;
    private String number;
    @Email(message = "Invalid Email")
    private String email;
    private String type;
    private String cnic;
    private InitialStatus status = InitialStatus.in_review;
}
