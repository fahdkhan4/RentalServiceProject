package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

}
