package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    HashSet<Roles> findByName(String name);
}
