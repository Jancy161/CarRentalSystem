

package com.hexaware.carrentalsystems.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.config.UserInfoUserDetailsService;
import com.hexaware.carrentalsystems.dto.AuthRequest;
import com.hexaware.carrentalsystems.dto.UserDto;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.repository.IUserRepository;
import com.hexaware.carrentalsystems.service.JwtService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserInfoUserDetailsService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // REGISTER ENDPOINT
    @PostMapping("/register")
    public String register(@RequestBody UserDto dto) {
        if (!userRepo.findByEmail(dto.getEmail()).isEmpty()) {
            return "User with this email already exists!";
        }
        User u = new User();
        u.setUserId(dto.getUserId());
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setRole(dto.getRole()); // Accepts Admin or User

        userRepo.save(u);
        logger.info("User registered successfully: {}", dto.getEmail());
        return "User registered successfully!";
    }

    // LOGIN ENDPOINT
	/*
	 * @PostMapping("/login") public String authenticateAndGetToken(@RequestBody
	 * AuthRequest authRequest) {
	 * 
	 * Authentication authentication = authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(authRequest.getUsername(),
	 * authRequest.getPassword()));
	 * 
	 * if (authentication.isAuthenticated()) { String token =
	 * jwtService.generateToken(authRequest.getUsername());
	 * logger.info("Generated Token: {}", token); return token; } else {
	 * logger.warn("Invalid login attempt for username: {}",
	 * authRequest.getUsername()); throw new
	 * UsernameNotFoundException("Name or Password is invalid / Invalid Request"); }
	 * }//returns jwt if successfully authorised
	 */
    
    @PostMapping("/login")
    public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            logger.info("Generated Token: {}", token);
            return Map.of("token", token); // return JSON
        } else {
            logger.warn("Invalid login attempt for username: {}", authRequest.getUsername());
            throw new UsernameNotFoundException("Email or Password is invalid / Invalid Request");
        }
    }
}
