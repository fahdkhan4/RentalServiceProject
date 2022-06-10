package com.example.RentalServiceProject.model;

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
    @NotEmpty
    @Size(min = 2,message = "Size of the asset should greater than 2 ")
    private String name;
    private Double pricePerDay;
    private String location;
    private String type;
    private String status;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "asset")
    @JsonIgnore
    private List<AssetReview> assetReview;

}
