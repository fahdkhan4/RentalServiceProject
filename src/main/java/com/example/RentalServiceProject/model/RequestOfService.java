package com.example.RentalServiceProject.model;

import com.example.RentalServiceProject.InitialStatus;
import lombok.*;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class RequestOfService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String details;
    @Enumerated(EnumType.STRING)
    private InitialStatus status = InitialStatus.in_review;
    @ManyToOne
    private User user;
}
