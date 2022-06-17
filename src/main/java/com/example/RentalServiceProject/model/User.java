package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.InitialStatus;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty
    @Size(min = 2,message = "Size should not be less than 2")
    private String name;
    private String number;

    @Email
    @NotEmpty
    private String email;
    private String type;

    @NotEmpty
    @Size(min = 6,message = "CNIC should not be less than 6 ")
    private String cnic;

    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;

}
