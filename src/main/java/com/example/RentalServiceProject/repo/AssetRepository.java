package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.dto.ForDate;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.model.Asset;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> , JpaSpecificationExecutor<Asset>{

    List<Asset> findByStatus(InitialStatus status, Pageable pageable);


    List<Asset> findByStatus(InitialStatus status);

//    List<Asset> getAssetByCriteria(AssetDto assetDto);
    @Query("SELECT city from Asset")
    List<String> getAssetCities();


    @Query(value = "SELECT a from Asset a where a.status = ?1 and a.city = ?2" +
            " and a.pricePerDay between ?3 and ?4 and start_date >= ?5 and end_date <= ?6")
    List<Asset> findByCriteria(InitialStatus published, String city,
                               Double startingPrice, Double endingPrice,
                               LocalDate startDate, LocalDate endDate);
    @Query(value = "SELECT  A.image\n" +
            "FROM AssetImages A, AssetImages B\n" +
            "WHERE A.id = B.id \n" +
            "and A.asset.id = ?1")
    List<String> getAllAssetImagesById(Long id);

    @Query(value = "SELECT a from Asset a where a.id = ?1")
    Asset getAssetById(Long id);
}
