package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public List<Asset>getAllAssets() {
        return assetRepository.findAll();
    }

    public List<Asset> getAssetByStatus(){
        return assetRepository.findByStatus(InitialStatus.Published);
    }

    public AssetDto addAsset_InDb(AssetDto assetDto) {
        return todto(assetRepository.save(dto(assetDto)));
    }


    public void deleteAsset_byId(Long id) {
        assetRepository.deleteById(id);
    }

    public Optional<Asset> getUser_ById(Long id) {
        return assetRepository.findById(id);
    }

    public AssetDto updateAsset_byId(Long id, AssetDto assetDto) {
        Asset update_asset =getAllAssets().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(update_asset != null){
            update_asset.setName(assetDto.getName());
            update_asset.setLocation(assetDto.getLocation());
            update_asset.setStatus(assetDto.getStatus());
            update_asset.setPricePerDay(assetDto.getPricePerDay());
            update_asset.setType(assetDto.getType());
            update_asset.setUser(assetDto.getUser());
        }
        return  todto(assetRepository.save(update_asset));
    }

    public Asset dto(AssetDto assetDto){
        return Asset.builder().Id(assetDto.getId()).name(assetDto.getName()).status(assetDto.getStatus()).location(assetDto.getLocation())
                .type(assetDto.getType()).pricePerDay(assetDto.getPricePerDay()).user(assetDto.getUser()).build();
    }

    public AssetDto todto(Asset asset){
        return AssetDto.builder().Id(asset.getId()).name(asset.getName()).status(asset.getStatus()).location(asset.getLocation())
                .type(asset.getType()).pricePerDay(asset.getPricePerDay()).user(asset.getUser()).build();
    }
}
