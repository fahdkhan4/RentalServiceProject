package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
       List<User> findByStatus(InitialStatus status);
       User findByNameAndStatus(String name,InitialStatus initialStatus);
       @Query("select distinct u from User u left join fetch u.roles where u.email = ?1")
       User findByEmail(String email);
}
