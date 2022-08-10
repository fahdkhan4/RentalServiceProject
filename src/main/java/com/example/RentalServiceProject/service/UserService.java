package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.config.FolderHandeler.ImageFolderHandeler;
import com.example.RentalServiceProject.config.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.repo.UserRepository;
import com.example.RentalServiceProject.repo.specification.UserSpecification;
import com.sun.javafx.iio.ImageStorageException;
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
public class UserService implements ImageStorage {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageFolderHandeler imageFolderHandeler;


    private final String imageFolderPath = Paths.get("src/main/resources/static/image/userimages").toString();

    public UserDto addingUser(UserDto userDto, MultipartFile image) {
//                                                                          Image Saved
            saveImage(image);
//
            String userImagePath = "http://localhost:8080/api/image/userimages/"+image.getOriginalFilename();
//
            userDto.setImage(userImagePath);
            return toDto(userRepository.save(dto(userDto)));
    }

    public List<User> getUsersbyStatus() {
        List<User> users = userRepository.findByStatus(InitialStatus.Published);
        if(!users.isEmpty()){
            return users;
        }
        throw new ContentNotFoundException("No User present in the record");
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {

        Optional<User> getUserById = userRepository.findById(id);
        if(getUserById.isPresent()){
            return getUserById;
        }
        throw new ContentNotFoundException("No User present with id of "+id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUserById(Long id, UserDto userDto) {

            User updateUser = getAllUsers().stream().filter(el -> el.getId().equals(id)).findAny().get();

            if (updateUser != null) {
                updateUser.setName(userDto.getName());
                updateUser.setEmail(userDto.getEmail());
                updateUser.setCnic(userDto.getCnic());
                updateUser.setNumber(userDto.getNumber());
                updateUser.setRoles(userDto.getRoles());
//                updateUser.setType(userDto.getType());
            }

         return toDto(userRepository.save(updateUser));
    }

    public List<UserDto> filteringUser(SearchCriteria criteria){
        UserSpecification userSpecification = new UserSpecification(criteria);
        List<User> getUser = userRepository.findAll(userSpecification);
        return getUser.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

    public User dto(UserDto dto){
        return User.builder().Id(dto.getId()).name(dto.getName()).image(dto.getImage()).cnic(dto.getCnic()).email(dto.getEmail())
                .status(dto.getStatus()).number(dto.getNumber()).roles(dto.getRoles()).build();
    }
    public UserDto toDto(User user){
        return UserDto.builder().Id(user.getId()).name(user.getName()).image(user.getImage()).cnic(user.getCnic()).email(user.getEmail())
                .status(user.getStatus()).number(user.getNumber()).roles(user.getRoles()).build();
    }


    @Override
    public InputStream getImageByName(String imageName) throws FileNotFoundException {

            String imagePath = imageFolderPath+File.separator+imageName;
            InputStream inputStream = new FileInputStream(imagePath);
            return inputStream;

    }

    @Override
    public void saveImage(MultipartFile image) {
//                                                              check if the image folder is exist
        imageFolderHandeler.creatingUserImagesFolder();
            try{
                Files.copy(image.getInputStream(), Paths.get(imageFolderPath+File.separator+image.getOriginalFilename()));
            }
            catch (Exception e){
                System.out.println("User Image Not Saved"+e);
            }
    }

}
