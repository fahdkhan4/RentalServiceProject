package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.dto.AssetBookingDto;
import com.example.RentalServiceProject.model.AssetBooking;
import com.example.RentalServiceProject.repo.AssetBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AssetBookingService {

    @Autowired
    AssetBookingRepository assetBookingRepository;

    public List<AssetBooking> getAllAssetBooking() {
        return assetBookingRepository.findAll();
    }

    public List<AssetBooking> getAssetBookingByStatus(){
        return assetBookingRepository.findByStatus(InitialStatus.Published);
    }

    public AssetBookingDto addAssetBooking_In_db(AssetBookingDto assetDto) {
        return todto(assetBookingRepository.save(dto(assetDto)));
    }

    public Optional<AssetBookingDto> updateAssetBooking_byId(Long id, AssetBookingDto assetDto) {
        AssetBooking updateAssetBooking = getAllAssetBooking().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(updateAssetBooking != null){
            updateAssetBooking.setUser(assetDto.getUser());
            updateAssetBooking.setAsset(assetDto.getAsset());
            updateAssetBooking.setStartDate(assetDto.getStartDate());
            updateAssetBooking.setEndDate(assetDto.getEndDate());
            updateAssetBooking.setPrice(assetDto.getPrice());
        }
        return Optional.of(todto(assetBookingRepository.save(updateAssetBooking)));
    }


    public Optional<AssetBooking> getAssetBooking_ById(Long id) {
        return assetBookingRepository.findById(id);
    }

    public void deleteAssetBooking_byId(Long id) {
        assetBookingRepository.deleteById(id);
    }

    public AssetBooking dto(AssetBookingDto assetBookingDto){
        return AssetBooking.builder().Id(assetBookingDto.getId()).status(assetBookingDto.getStatus()).user(assetBookingDto.getUser())
                .endDate(assetBookingDto.getEndDate()).startDate(assetBookingDto.getStartDate()).asset(assetBookingDto.getAsset())
                .price(assetBookingDto.getPrice()).build();
    }

    public AssetBookingDto todto(AssetBooking assetBooking){
        return  AssetBookingDto.builder().Id(assetBooking.getId()).status(assetBooking.getStatus()).user(assetBooking.getUser())
                .endDate(assetBooking.getEndDate()).startDate(assetBooking.getStartDate()).asset(assetBooking.getAsset())
                .price(assetBooking.getPrice()).build();
    }

}
