package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.dto.AssetReviewDto;
import com.example.RentalServiceProject.model.AssetReview;
import com.example.RentalServiceProject.repo.AssetReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetReviewService {

    @Autowired
    AssetReviewRepository assetReviewRepository;


    public List<AssetReview> getAllAssetBooking() {
        return assetReviewRepository.findAll();
    }

    public AssetReviewDto addAssetBooking_In_db(AssetReviewDto assetbookingDto) {
        return todto(assetReviewRepository.save(dto(assetbookingDto)));
    }

    public Optional<AssetReview> getAssetBooking_ById(Long id) {
        return assetReviewRepository.findById(id);
    }

    public Optional<AssetReviewDto> updateAssetBooking_byId(Long id, AssetReviewDto assetDto) {
        AssetReview assetReview = getAllAssetBooking().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(assetReview != null){
            assetReview.setAsset(assetDto.getAsset());
            assetReview.setReview(assetDto.getReview());
            assetReview.setStatus(assetDto.getStatus());
            assetReview.setUser(assetDto.getUser());
            assetReview.setRating(assetDto.getRating());
        }
        return Optional.of(todto(assetReviewRepository.save(assetReview)));
    }

    public void deleteAssetBooking_byId(Long id) {
        assetReviewRepository.deleteById(id);
    }

    public AssetReview dto(AssetReviewDto assetReviewDto){
        return AssetReview.builder().id(assetReviewDto.getId()).asset(assetReviewDto.getAsset()).review(assetReviewDto.getReview())
                .status(assetReviewDto.getStatus()).user(assetReviewDto.getUser()).rating(assetReviewDto.getRating()).build();
    }

    public AssetReviewDto todto(AssetReview assetReview){
        return AssetReviewDto.builder().id(assetReview.getId()).asset(assetReview.getAsset()).review(assetReview.getReview())
                .status(assetReview.getStatus()).user(assetReview.getUser()).rating(assetReview.getRating()).build();
    }
}

