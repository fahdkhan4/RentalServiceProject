package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.AssetBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetBookingRepository extends JpaRepository<AssetBooking,Long> {

    public List<AssetBooking> findByStatus(InitialStatus status);

}
