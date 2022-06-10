package com.example.RentalServiceProject.dto;

import lombok.*;

import javax.validation.constraints.*;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDto {

    private Long Id;
    private String name;
    private String number;
    private String email;
    private String type;
    private String cnic;
    private String status;

}
