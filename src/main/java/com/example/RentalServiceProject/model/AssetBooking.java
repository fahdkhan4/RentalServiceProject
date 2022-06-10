package com.example.RentalServiceProject.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

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
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private String status;

}
