package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.AssetReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetReviewRepository extends JpaRepository<AssetReview,Long> {

}
