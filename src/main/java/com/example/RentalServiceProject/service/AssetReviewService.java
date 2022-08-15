package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.AssetReviewDto;
import com.example.RentalServiceProject.model.AssetReview;
import com.example.RentalServiceProject.repo.AssetReviewRepository;
import com.example.RentalServiceProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetReviewService {

    @Autowired
    AssetReviewRepository assetReviewRepository;
    @Autowired
    UserService userService;
    @Autowired
    AssetService assetService;


    public List<AssetReview> getAllAssetReview() {
        return assetReviewRepository.findAll();
    }

    public List<AssetReview> getAssetReviewByStatus(){
        List<AssetReview> assetReviews = assetReviewRepository.findByStatus(InitialStatus.Published);
        if(!assetReviews.isEmpty()){
            return assetReviews;
        }
        throw new ContentNotFoundException("No Asset Review Found in Record");
    }

    public AssetReviewDto addAssetReview_In_db(AssetReviewDto assetbookingDto) {
        User user = userService.getUsersbyStatus().stream().filter(user1 -> user1.getId().equals(assetbookingDto.getUser().getId())).findAny().get();
        Asset asset = assetService.getAssetByStatus().stream().filter(asset1 -> asset1.getId().equals(assetbookingDto.getAsset().getId())).findAny().get();
        assetbookingDto.setUser(user);
        assetbookingDto.setAsset(asset);
        return todto(assetReviewRepository.save(dto(assetbookingDto)));
    }

    public Optional<AssetReview> getAssetReview_ById(Long id) {
        return assetReviewRepository.findById(id);
    }

    public Optional<AssetReviewDto> updateAssetReview_byId(Long id, AssetReviewDto assetDto) {
        AssetReview assetReview = getAllAssetReview().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(assetReview != null){
            assetReview.setReview(assetDto.getReview());
            assetReview.setRating(assetDto.getRating());
        }
        return Optional.of(todto(assetReviewRepository.save(assetReview)));
    }

    public void deleteAssetReview_byId(Long id) {
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

