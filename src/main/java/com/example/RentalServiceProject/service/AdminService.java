package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.dto.*;
import com.example.RentalServiceProject.model.*;
import com.example.RentalServiceProject.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AssetRepository assetRepository;
    @Autowired
    AssetBookingRepository assetBookingRepository;
    @Autowired
    AssetReviewRepository assetReviewRepository;
    @Autowired
    UserRatingRepository userRatingRepository;
    @Autowired
    RequestOfServiceRepository requestOfServiceRepository;

    @Autowired
    UserService userService;
    @Autowired
    AssetService assetService;
    @Autowired
    AssetBookingService assetBookingService;
    @Autowired
    AssetReviewService assetReviewService;
    @Autowired
    UserRatingService userRatingAndReviewService;
    @Autowired
    RequestOfServiceService requestOfServiceService;


    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    public UserDto updateUser_Status(Long id, UserDto userDto) {
        User updateUser = userService.getAllUsers().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(updateUser != null){
            updateUser.setStatus(userDto.getStatus());
        }
        return  userService.toDto(userRepository.save(updateUser));
    }

    public AssetDto updateAsset_Status(Long id, AssetDto assetDto) {
        Asset asset = assetService.getAllAssets().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(asset != null){
            asset.setStatus(assetDto.getStatus());
        }
        return assetService.todto(assetRepository.save(asset));
    }


    public AssetBookingDto updateAssetBooking_Status(Long id, AssetBookingDto assetBookingDto) {
        AssetBooking assetBooking = assetBookingService.getAllAssetBooking().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(assetBooking != null){
            assetBooking.setStatus(assetBookingDto.getStatus());
        }
        return assetBookingService.todto(assetBookingRepository.save(assetBooking));
    }

    public AssetReviewDto updateAssetReview_Status(Long id, AssetReviewDto assetReviewDto) {
        AssetReview assetReview = assetReviewService.getAllAssetReview().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(assetReview != null){
            assetReview.setStatus(assetReviewDto.getStatus());
        }
        return assetReviewService.todto(assetReviewRepository.save(assetReview));
    }

    public RequestOfServiceDto updateRequestofService_Status(Long id, RequestOfServiceDto updateRequestOfService) {
        RequestOfService requestOfService = requestOfServiceService.getAllRequestOfService().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(requestOfService != null){
            requestOfService.setStatus(updateRequestOfService.getStatus());
        }
        return requestOfServiceService.todto(requestOfServiceRepository.save(requestOfService));
    }


    public UserRatingDto updateUserRating_Status(Long id, UserRatingDto userRatingDto) {
        UserRatingAndReview userRatingAndReview = userRatingAndReviewService.getAllUserRatingAndReviews().stream().filter(el->el.getUserRatingAndReviewId().equals(id)).findAny().get();
        if(userRatingAndReview != null){
            userRatingAndReview.setStatus(userRatingDto.getStatus());
        }
        return userRatingAndReviewService.todto(userRatingRepository.save(userRatingAndReview));
    }

}
