package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.RequestOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestOfServiceRepository extends JpaRepository<RequestOfService,Long> {

    public List<RequestOfService> findByStatus(InitialStatus status);
}
