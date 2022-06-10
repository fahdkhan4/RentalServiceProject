package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.InitialStatus;
import com.example.RentalServiceProject.dto.UserDto;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll_Users(){
        return userRepository.findAll();
    }

    public UserDto updateUser_Status(Long id, UserDto userDto) {
        User updateUser = getAll_Users().stream().filter(el->el.getId().equals(id)).findAny().get(); ;
        if(updateUser != null){
            updateUser.setStatus(userDto.getStatus());
        }
        return toDto(userRepository.save(updateUser));
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
