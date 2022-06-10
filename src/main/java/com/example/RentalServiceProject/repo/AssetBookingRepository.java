package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.AssetBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetBookingRepository extends JpaRepository<AssetBooking,Long> {
}
