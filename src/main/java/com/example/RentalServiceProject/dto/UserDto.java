package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.model.Roles;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;


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
    private String image;
    @Email(message = "Invalid Email")
    private String email;
    private String cnic;
    private Set<Roles> roles = new HashSet<Roles>();
    private InitialStatus status = InitialStatus.in_review;

    public void addRole(Roles role){
        this.roles.add(role);
    }
}
