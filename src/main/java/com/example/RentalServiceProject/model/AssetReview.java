package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.InitialStatus;
import com.sun.org.apache.xml.internal.security.Init;
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
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;
    @ManyToOne
    private Asset asset;
    @ManyToOne
    private User user;
}
