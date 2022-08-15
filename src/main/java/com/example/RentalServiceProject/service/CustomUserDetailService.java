package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.dto.CustomUserDetail;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.repo.UserRepository;
import com.example.RentalServiceProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByName(username);
       if(user == null){
           throw new UsernameNotFoundException("No User");
       }
       return new CustomUserDetail(user);
    }

}
