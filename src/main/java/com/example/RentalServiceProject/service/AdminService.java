package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.dto.*;
import com.example.RentalServiceProject.model.*;
import com.example.RentalServiceProject.repo.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

//                                                                          Construction injection of repositories

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final AssetBookingRepository assetBookingRepository;
    private final AssetReviewRepository assetReviewRepository;
    private final UserRatingRepository userRatingRepository;
    private final RequestOfServiceRepository requestOfServiceRepository;
    private final RolesRepository rolesRepository;
    private final AdminRepository adminRepository;
//                                                                          Construction injection of Services
    private final UserService userService;
    private final AssetService assetService;
    private final AssetBookingService assetBookingService;
    private final AssetReviewService assetReviewService;
    private final UserRatingService userRatingAndReviewService;
    private final RequestOfServiceService requestOfServiceService;

    public AdminService(UserRepository userRepository, AssetRepository assetRepository, AssetBookingRepository assetBookingRepository, AssetReviewRepository assetReviewRepository, UserRatingRepository userRatingRepository, RequestOfServiceRepository requestOfServiceRepository, UserService userService, AssetService assetService, AssetBookingService assetBookingService, AssetReviewService assetReviewService, UserRatingService userRatingAndReviewService, RequestOfServiceService requestOfServiceService, RolesRepository rolesRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
        this.assetBookingRepository = assetBookingRepository;
        this.assetReviewRepository = assetReviewRepository;
        this.userRatingRepository = userRatingRepository;
        this.requestOfServiceRepository = requestOfServiceRepository;
        this.userService = userService;
        this.assetService = assetService;
        this.assetBookingService = assetBookingService;
        this.assetReviewService = assetReviewService;
        this.userRatingAndReviewService = userRatingAndReviewService;
        this.requestOfServiceService = requestOfServiceService;
        this.rolesRepository = rolesRepository;
        this.adminRepository = adminRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public UserDto updateUser_Status(Long id, UserDto userDto) {
        User updateUser = getAllUsers().stream().filter(el->el.getId().equals(id)).findAny().get();

        if(updateUser != null){
            updateUser.setStatus(userDto.getStatus());
        }
        return  userService.toDto(userRepository.save(updateUser));
    }


    public List<AssetDto> getAllAssets() {
        List<AssetDto> assetDtos = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 4);
        List<Asset> assetList = adminRepository.findEach(pageable);
//        List<Asset> asset = assetRepository.;
        for(Asset asset1: assetList){
            assetDtos.add(todto(asset1));
        }
        return assetDtos;
    }

//    public AssetDto updateAsset_Status(Long id, AssetDto assetDto) {
//
//        Asset asset = getAllAssets().stream().filter(el->el.getId().equals(id)).findAny().get();
//        if(asset != null){
//            asset.setStatus(assetDto.getStatus());
//        }
//        return assetService.todto(assetRepository.save(asset));
//    }


    public List<AssetBooking> getAllAssetBooking() {
        return assetBookingRepository.findAll();
    }

    public AssetBookingDto updateAssetBooking_Status(Long id, AssetBookingDto assetBookingDto) {
        AssetBooking assetBooking = getAllAssetBooking().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(assetBooking != null){
            assetBooking.setStatus(assetBookingDto.getStatus());
        }
        return assetBookingService.todto(assetBookingRepository.save(assetBooking));
    }

    public List<AssetReview> getAllAssetReview(){
        return  assetReviewRepository.findAll();
    }

    public AssetReviewDto updateAssetReview_Status(Long id, AssetReviewDto assetReviewDto) {
        AssetReview assetReview = assetReviewService.getAllAssetReview().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(assetReview != null){
            assetReview.setStatus(assetReviewDto.getStatus());
        }
        return assetReviewService.todto(assetReviewRepository.save(assetReview));
    }

    public List<RequestOfService> getAllRequestOfService(){
        return requestOfServiceRepository.findAll();
    }

    public RequestOfServiceDto updateRequestofService_Status(Long id, RequestOfServiceDto updateRequestOfService) {
        RequestOfService requestOfService = requestOfServiceService.getAllRequestOfService().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(requestOfService != null){
            requestOfService.setStatus(updateRequestOfService.getStatus());
        }
        return requestOfServiceService.todto(requestOfServiceRepository.save(requestOfService));
    }

    public List<UserRatingAndReview> getAllUserRatingAndReview(){
        return userRatingRepository.findAll();
    }

    public UserRatingDto updateUserRating_Status(Long id, UserRatingDto userRatingDto) {
        UserRatingAndReview userRatingAndReview = getAllUserRatingAndReview().stream().filter(el->el.getUserRatingAndReviewId().equals(id)).findAny().get();
        if(userRatingAndReview != null){
            userRatingAndReview.setStatus(userRatingDto.getStatus());
        }
        return userRatingAndReviewService.todto(userRatingRepository.save(userRatingAndReview));
    }


    public Asset dto(AssetDto assetDto){
        return Asset.builder()
                .Id(assetDto.getId())
                .name(assetDto.getName())
                .status(assetDto.getStatus())
                .image(assetDto.getImage())
                .address(assetDto.getAddress())
                .type(assetDto.getType())
                .pricePerDay(assetDto.getPricePerDay())
                .startDate(LocalDate.now())
                .endDate(assetDto.getEndDate())
                .user(assetDto.getUser()).build();
    }

    public AssetDto todto(Asset asset){
        return AssetDto.builder()
                .Id(asset.getId())
                .name(asset.getName())
                .status(asset.getStatus())
                .image(asset.getImage())
                .address(asset.getAddress())
                .type(asset.getType())
                .pricePerDay(asset.getPricePerDay())
                .city(asset.getCity())
                .startDate(asset.getStartDate())
                .endDate(asset.getEndDate())
                .user(asset.getUser())
                .build();
    }


    public List<String> getAllAssetType() {
        List<String> assetTypes =  assetRepository.getAllAssetType();
        return assetService.removeDuplicates(assetTypes);
    }
}
