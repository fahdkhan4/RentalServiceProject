package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

       public  List<User> findByStatus(InitialStatus status);
}
