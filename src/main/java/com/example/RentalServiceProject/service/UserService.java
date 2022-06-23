package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.dto.SearchCriteria;
import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.repo.UserRepository;
import com.example.RentalServiceProject.repo.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto addingUser(UserDto userDto) {
        return toDto(userRepository.save(dto(userDto)));
    }

    public List<User> getUsersbyStatus() {
        return userRepository.findByStatus(InitialStatus.Published);
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return  userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserDto> updateUserById(Long id, UserDto userDto) {
        User updateUser = getAllUsers().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(updateUser != null){
           updateUser.setName(userDto.getName());
           updateUser.setEmail(userDto.getEmail());
           updateUser.setCnic(userDto.getCnic());
           updateUser.setNumber(userDto.getNumber());
           updateUser.setType(userDto.getType());
        }
        return Optional.of(toDto(userRepository.save(updateUser)));
    }

    public List<UserDto> filteringUser(SearchCriteria criteria){
        UserSpecification userSpecification = new UserSpecification(criteria);
        List<User> getUser = userRepository.findAll(userSpecification);
        return getUser.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

    public User dto(UserDto dto){
        return User.builder().Id(dto.getId()).name(dto.getName()).cnic(dto.getCnic()).email(dto.getEmail())
                .status(dto.getStatus()).number(dto.getNumber()).type(dto.getType()).build();
    }
    public UserDto toDto(User user){
        return UserDto.builder().Id(user.getId()).name(user.getName()).cnic(user.getCnic()).email(user.getEmail())
                .status(user.getStatus()).number(user.getNumber()).type(user.getType()).build();
    }

}
