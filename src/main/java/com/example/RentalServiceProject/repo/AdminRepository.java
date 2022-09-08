package com.example.RentalServiceProject.repo;

import com.example.RentalServiceProject.model.Asset;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Asset, Long> {

    @Query(value = "from Asset a")
    List<Asset> findEach(Pageable pageable);
}
