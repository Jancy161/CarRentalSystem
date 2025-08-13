package com.hexaware.carrentalsystems.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.carrentalsystems.config.UserInfoUserDetailsService;
import com.hexaware.carrentalsystems.dto.AuthRequest;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserInfoUserDetailsService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

   

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            logger.info("Generated Token: {}", token);
            return token;
        } else {
            logger.info("Invalid login attempt");
            throw new UsernameNotFoundException("Name or Password is invalid / Invalid Request");
        }
    }
}

        // return JWT token if login success
    

