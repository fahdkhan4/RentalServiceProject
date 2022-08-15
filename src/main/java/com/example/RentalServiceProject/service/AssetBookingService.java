package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.AssetBookingDto;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.model.AssetBooking;
import com.example.RentalServiceProject.repo.AssetBookingRepository;
import com.example.RentalServiceProject.repo.specification.AssetBookingSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetBookingService {

    @Autowired
    AssetBookingRepository assetBookingRepository;
    @Autowired
    UserService userService;
    @Autowired
    AssetService assetService;

    public List<AssetBooking> getAllAssetBooking() {
        List<AssetBooking> assetBookings = assetBookingRepository.findAll();
        if(!assetBookings.isEmpty()){
            return assetBookings;
        }
        throw new ContentNotFoundException("No AssetBookings Present in the record");
    }

    public List<AssetBooking> getAssetBookingByStatus(){
        List<AssetBooking> assetBookings = assetBookingRepository.findByStatus(InitialStatus.Published);
        if(!assetBookings.isEmpty()){
            return assetBookings;
        }
        throw new ContentNotFoundException("No AssetBookings Present in the record");
    }

    public AssetBookingDto addAssetBooking_In_db(AssetBookingDto assetBookingDto) {
        User userdetails = userService.getUsersbyStatus().stream().filter(user -> assetBookingDto.getUser().getId().equals(user.getId())).findAny().get();
        Asset assetdetails  = assetService.getAssetByStatus().stream().filter(asset -> assetBookingDto.getAsset().getId().equals(asset.getId())).findAny().get();

        assetBookingDto.setUser(userdetails);
        assetBookingDto.setAsset(assetdetails);

        return todto(assetBookingRepository.save(dto(assetBookingDto)));
    }

    public Optional<AssetBookingDto> updateAssetBooking_byId(Long id, AssetBookingDto assetDto) {
        AssetBooking updateAssetBooking = getAllAssetBooking().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(updateAssetBooking != null){
//            updateAssetBooking.setUser(assetDto.getUser());
//            updateAssetBooking.setAsset(assetDto.getAsset());
            updateAssetBooking.setStartDate(assetDto.getStartDate());
            updateAssetBooking.setEndDate(assetDto.getEndDate());
            updateAssetBooking.setPrice(assetDto.getPrice());
        }
        return Optional.of(todto(assetBookingRepository.save(updateAssetBooking)));
    }


    public Optional<AssetBooking> getAssetBooking_ById(Long id) {
        Optional<AssetBooking> assetBooking = assetBookingRepository.findById(id);
        if(assetBooking.isPresent()){
            return assetBooking;
        }
        throw new ContentNotFoundException("No AssetBooking Found with id "+id);
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

    public List<AssetBookingDto> getFilteredAssetBooking(SearchCriteria searchCriteria) {
        AssetBookingSpecification assetBookingSpecification = new AssetBookingSpecification(searchCriteria);
        List<AssetBooking> assetBookings = assetBookingRepository.findAll(assetBookingSpecification);
        return assetBookings.stream().map(el->todto(el)).collect(Collectors.toList());
    }
}
