package com.example.RentalServiceProject.model;


import com.example.RentalServiceProject.model.enums.InitialStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class AssetBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    private Asset asset;
    @ManyToOne
    private User user;
    private String startDate;
    private String endDate;
    private Double price;
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;

}
