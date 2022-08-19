package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.configuration.FolderHandeler.ImageFolderHandeler;
import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.Roles;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.repo.RolesRepository;
import com.example.RentalServiceProject.repo.UserRepository;
import com.example.RentalServiceProject.repo.specification.UserSpecification;
import com.sun.javafx.iio.ImageStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements ImageStorage {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageFolderHandeler imageFolderHandeler;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RolesRepository rolesRepository;


    private final String imageFolderPath = Paths.get("src/main/resources/static/image/userimages").toString();
    private final String userImageLocation =  "http://localhost:8080/api/image/userimages/";

    public UserDto saveUserInDb(UserDto userDto, MultipartFile image) {
            Set<Roles> roles = new HashSet<>();
//                                                                          Image Saved
            saveImage(image);
//                                                                          Setting image path to save in database
            String userImagePath = userImageLocation+image.getOriginalFilename();
            userDto.setImage(userImagePath);
//                                                                          Encrypting password
            userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//                                                                          Assigning Role and Status to user
            if(userDto.getType().equalsIgnoreCase("SERVICE_PROVIDER")){

                userDto.setStatus(InitialStatus.in_review);
                roles.add(rolesRepository.findByName("ROLE_SERVICE_PROVIDER"));
                roles.add(rolesRepository.findByName("ROLE_CUSTOMER"));
                userDto.setRoles(roles);
            }
            else{
                userDto.setStatus(InitialStatus.Published);
                roles.add(rolesRepository.findByName("ROLE_CUSTOMER"));
                userDto.setRoles(roles);
            }
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
            }

         return toDto(userRepository.save(updateUser));
    }

    public List<UserDto> filteringUser(SearchCriteria criteria){
        UserSpecification userSpecification = new UserSpecification(criteria);
        List<User> getUser = userRepository.findAll(userSpecification);
        return getUser.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

    public User dto(UserDto dto){
        return User.builder().Id(dto.getId()).name(dto.getName()).password(dto.getPassword()).image(dto.getImage()).cnic(dto.getCnic()).email(dto.getEmail())
                .status(dto.getStatus()).number(dto.getNumber()).roles(dto.getRoles()).build();
    }
    public UserDto toDto(User user){
        return UserDto.builder().Id(user.getId()).name(user.getName()).image(user.getImage()).cnic(user.getCnic()).email(user.getEmail())
                .status(user.getStatus()).number(user.getNumber()).roles(user.getRoles()).build();
    }
//                                                                Get user image from the disk
    @Override
    public InputStream getImageByName(String imageName) throws FileNotFoundException {
            String imagePath = imageFolderPath+File.separator+imageName;
            InputStream inputStream = new FileInputStream(imagePath);
            return inputStream;
    }
//                                                                Save User image in Disk
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
