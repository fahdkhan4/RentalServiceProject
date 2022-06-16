package com.example.RentalServiceProject.dto;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.repo.RequestOfServiceRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RequestOfServiceDto {

    private Long id;
    private String type;
    private String details;
    private InitialStatus status = InitialStatus.in_review;
    private User user;

}
