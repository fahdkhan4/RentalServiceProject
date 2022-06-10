package com.example.RentalServiceProject.model;

import lombok.*;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class AssetReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rating;
    private String review;
    private String status;
    @ManyToOne
    private Asset asset;
    @ManyToOne
    private User user;
}
