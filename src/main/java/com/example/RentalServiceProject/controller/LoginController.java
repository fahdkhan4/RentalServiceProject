package com.example.RentalServiceProject.controller;

import com.example.RentalServiceProject.dto.AuthenticationResponse;
import com.example.RentalServiceProject.dto.LoginCredentials;
import com.example.RentalServiceProject.util.JwtUtil;
import com.example.RentalServiceProject.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@Controller
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;
//

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse>  createAuthenticationToken(@RequestBody  LoginCredentials loginCredentials) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(),loginCredentials.getPassword()));
        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect Username and Password !",e);
        }
            UserDetails userDetails = customUserDetailService.loadUserByUsername(loginCredentials.getEmail());
        String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
