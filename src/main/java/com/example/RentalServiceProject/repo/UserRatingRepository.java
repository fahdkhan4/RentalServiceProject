package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.UserRatingAndReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRatingAndReview,Long> {
}
