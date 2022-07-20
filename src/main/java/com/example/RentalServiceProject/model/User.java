package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.model.enums.InitialStatus;
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
    private String name;
    private String number;
    private String email;
    private String type;
    private String cnic;
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;


}
