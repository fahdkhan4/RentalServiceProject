package com.example.RentalServiceProject.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Component
public class LoginCredentials {

    private String email;
    private String password;

}
