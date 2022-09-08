package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.configuration.FolderHandeler.ImageFolderHandeler;
import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
//                                                                                      Location Of AssetImages Folder
    public final String assetFolderPath = Paths.get("src/main/resources/static/image/assetimages").toString();
//                                                                                      Path of Asset Image to store in DB
    public final String assetImageFileLocation = "http://localhost:8081/api/image/assetimages/";

    public List<Asset> getAllAssets() {
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
        throw new ContentNotFoundException("No Asset Found in Record ");
    }
    public List<Asset> getAssetByStatus(Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Asset> assetList = assetRepository.findByStatus(InitialStatus.Published, pageable);
        if(!assetList.isEmpty()){
            return assetList;
        }
        throw new ContentNotFoundException("No Asset Found in Record ");
    }

    public AssetDto addAsset_InDb(AssetDto assetDto,MultipartFile image) {

        User getAssetUser = userService.getUsersbyStatus().stream().filter(el -> el.getId().equals(assetDto.getUser().getId())).findAny().get();
        assetDto.setUser(getAssetUser);
//                                                                  Save Asset image
        saveImage(image);
//                                                                  setting path of image to store in Database
        String assetImagePath  = assetImageFileLocation+image.getOriginalFilename();
        assetDto.setImage(assetImagePath);
//                                                         Save Asset in Database
        return todto(assetRepository.save(dto(assetDto)));
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
        Asset update_asset = getAllAssets().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(update_asset != null){
            update_asset.setName(assetDto.getName());
            update_asset.setAddress(assetDto.getAddress());
            update_asset.setPricePerDay(assetDto.getPricePerDay());
            update_asset.setType(assetDto.getType());
        }
        return  todto(assetRepository.save(update_asset));
    }


    public List<AssetDto> search(SearchCriteria search) {
        AssetSpecification as = new AssetSpecification(search);
        List<Asset> assets =  assetRepository.findAll(as);
        return assets.stream().map(asset -> todto(asset)).collect(Collectors.toList());
    }
    //                                                                Get asset image from the disk
    @Override
    public InputStream getImageByName(String filename) throws FileNotFoundException {
        String assetImagePath = assetFolderPath+File.separator+filename;
        InputStream inputStream = new FileInputStream(assetImagePath);
        return inputStream;
    }
//                                                                    Save Asset Image to the disk
    @Override
    public void saveImage(MultipartFile image) {
        imageFolderHandeler.creatingAssetImagesFolder();

        try{
            Files.copy(image.getInputStream(),Paths.get(assetFolderPath+File.separator+image.getOriginalFilename()));

        }catch (Exception e){
            System.out.println(e);
        }
    }
//                                                                          Add Multiple Asset images in AssetImages Database
    public AssetImages uploadAssetImage(Long id,MultipartFile image) {
        System.out.println(id);
        Asset asset = assetRepository.getAssetById(id);
//                                                                          Image will be Saved in Disk
        saveImage(image);
//                                                                          Creating path of assetImage to store in DB
        String assetImagePath  = assetImageFileLocation+image.getOriginalFilename();
//                                                                          Creating AssetImage Object to save assetimage in database
        AssetImages assetImages = new AssetImages();
        assetImages.setImage(assetImagePath);
        assetImages.setAsset(asset);

        return  assetImageRepository.save(assetImages);
    }
    public List<String> getAssetCities(){
        List<String> cities =  assetRepository.getAssetCities();
        return removeDuplicates(cities);
    }

    public List<String> removeDuplicates(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String city : list) {
            if (!newList.contains(city)) {
                newList.add(city);
            }
        }
        return newList;
    }

    public Asset dto(AssetDto assetDto){
        return Asset.builder()
                .Id(assetDto.getId())
                .name(assetDto.getName())
                .status(assetDto.getStatus())
                .image(assetDto.getImage())
                .address(assetDto.getAddress())
                .type(assetDto.getType())
                .city(assetDto.getCity())
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
                .user(asset.getUser())
                .build();
    }

    public List<AssetDto> getAssetByCriteria(AssetDto assetDto, Integer pageNumber, Integer pageSize) {
        List<AssetDto> assetDtos = new ArrayList<>();
        List<Asset> assetList;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        try {
            assetList = assetRepository.findByCriteria(InitialStatus.Published, assetDto.getCity(),
                        assetDto.getStartingPrice(),
                    assetDto.getEndingPrice(),
                    assetDto.getStartDate(),
                    assetDto.getEndDate());
            if (!assetList.isEmpty()) {
                for(Asset asset: assetList){
                    assetDtos.add(todto(asset));
                }
                return assetDtos;
            }
        } catch (Exception ex) {
            throw new ContentNotFoundException("No Asset Found in Record ");
        }
        return  null;
    }

    public List<String> getAllAssetImagesById(Long id) {
        List<String> assetImages = new ArrayList<>();
        assetImages = assetRepository.getAllAssetImagesById(id);
        return assetImages;
    }
}
