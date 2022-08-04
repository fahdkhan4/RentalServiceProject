package com.example.RentalServiceProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor

@Entity
public class AssetImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    @ManyToOne
    private Asset asset;

}
