package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    public List<Asset> findByStatus(InitialStatus status);
}
