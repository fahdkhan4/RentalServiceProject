package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.AssetImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetImageRepository extends JpaRepository<AssetImages,Long> {

    @Query(value = "SELECT * from asset_images a where a.asset_id = ?1 ",nativeQuery = true)
    List<AssetImages> findbyasset(Long id);

}
