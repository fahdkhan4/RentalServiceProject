package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.config.FolderHandeler.ImageFolderHandeler;
import com.example.RentalServiceProject.config.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.AssetImages;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.AssetDto;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.model.Asset;
import com.example.RentalServiceProject.repo.AssetImageRepository;
import com.example.RentalServiceProject.repo.AssetRepository;
import com.example.RentalServiceProject.repo.specification.AssetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssetService implements ImageStorage {

    @Autowired
    AssetRepository assetRepository;
    @Autowired
    UserService userService;
    @Autowired
    ImageFolderHandeler imageFolderHandeler;
    @Autowired
    AssetImageRepository assetImageRepository;

    public final String assetFolderPath = Paths.get("src/main/resources/static/image/assetimages").toString();

    public List<Asset>getAllAssets() {
        List<Asset> assetList = assetRepository.findAll();
        if(!assetList.isEmpty()){
            return assetList;
        }
        throw new ContentNotFoundException("No Asset Found in Record");
    }

    public List<Asset> getAssetByStatus(){
        List<Asset> assetList = assetRepository.findByStatus(InitialStatus.Published);
        if(!assetList.isEmpty()){
            return assetList;
        }
        throw new ContentNotFoundException("No Asset Found in Round ");
    }

    public AssetDto addAsset_InDb(AssetDto assetDto,MultipartFile image) {

        User getAssetUser = userService.getUsersbyStatus().stream().filter(el -> el.getId().equals(assetDto.getUser().getId())).findAny().get();
//                                                                  Save Asset image
        saveImage(image);
//                                                                  setting path of image to store in Database
        String assetImagePath  = "http://localhost:8080/api/image/assetimages/"+image.getOriginalFilename();
        assetDto.setUser(getAssetUser);
//                                                                  Save Asset in Database
        Asset assetDetails = assetRepository.save(dto(assetDto));
//                                                                  Save AssetImage in Database
        AssetImages assetImages = new AssetImages();
        assetImages.setImage(assetImagePath);
        assetImages.setAsset(assetDetails);
        assetImageRepository.save(assetImages);

        AssetDto assetDtoToSaveImageUrl = todto(assetDetails);
        assetDtoToSaveImageUrl.setImage(assetImagePath);

        return assetDtoToSaveImageUrl;
    }


    public void deleteAsset_byId(Long id) {
        assetRepository.deleteById(id);
    }

    public Optional<Asset> getUser_ById(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent()){
            return asset;
        }
        throw new ContentNotFoundException("No Asset Found with id "+id);
    }

    public AssetDto updateAsset_byId(Long id, AssetDto assetDto) {
        Asset update_asset =getAllAssets().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(update_asset != null){
            update_asset.setName(assetDto.getName());
            update_asset.setLocation(assetDto.getLocation());
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

    public List<AssetDto> search(SearchCriteria search) {
        AssetSpecification as = new AssetSpecification(search);
        List<Asset> assets =  assetRepository.findAll(as);
        return assets.stream().map(asset -> todto(asset)).collect(Collectors.toList());
    }

    @Override
    public InputStream getImageByName(String filename) throws FileNotFoundException {
        String assetImagePath = assetFolderPath+File.separator+filename;
        InputStream inputStream = new FileInputStream(assetImagePath);
        return inputStream;
    }

    @Override
    public void saveImage(MultipartFile image) {
        imageFolderHandeler.creatingAssetImagesFolder();

        try{
            Files.copy(image.getInputStream(),Paths.get(assetFolderPath+File.separator+image.getOriginalFilename()));

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
