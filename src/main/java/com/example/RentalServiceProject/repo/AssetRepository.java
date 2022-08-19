package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> , JpaSpecificationExecutor<Asset>{

    List<Asset> findByStatus(InitialStatus status);

//    List<Asset> getAssetByCriteria(AssetDto assetDto);
    @Query("SELECT city from Asset")
    List<String> getAssetCities();
}
