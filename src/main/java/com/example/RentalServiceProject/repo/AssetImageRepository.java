package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.AssetImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetImageRepository extends JpaRepository<AssetImages,Long> {


}
