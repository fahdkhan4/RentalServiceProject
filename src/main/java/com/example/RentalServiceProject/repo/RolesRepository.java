package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByName(String name);
}
