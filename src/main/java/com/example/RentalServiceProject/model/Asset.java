package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Double pricePerDay;
    private String image;
    private String address;
    private String type;
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "asset")
    @JsonIgnore
    private List<AssetReview> assetReview;

}
