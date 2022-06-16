package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.AssetReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetReviewRepository extends JpaRepository<AssetReview,Long> {

    public List<AssetReview> findByStatus(InitialStatus status);
}
