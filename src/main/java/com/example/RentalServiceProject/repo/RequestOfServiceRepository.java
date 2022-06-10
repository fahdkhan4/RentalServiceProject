package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.RequestOfService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestOfServiceRepository extends JpaRepository<RequestOfService,Long> {
}
