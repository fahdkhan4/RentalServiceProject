package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.UserRatingAndReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRatingAndReview,Long> {

    public List<UserRatingAndReview> findByStatus(InitialStatus status);
}
