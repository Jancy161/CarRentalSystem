package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.dto.UserDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    IUserService service;

    @PostMapping("/insert")
   // @PreAuthorize("hasAnyAuthority('Admin','User')")
    public User addUser(@RequestBody @Valid UserDto dto) {
        log.info("Received request to add user: {}", dto);

        return service.addUser(dto);
    }
   
    

    @PutMapping("/update/{userId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public User updateUser(@PathVariable int userId,@RequestBody @Valid UserDto dto) {
        log.info("Received request to fetch user with ID: {}");

        return service.updateUser(userId, dto);
    }

    @GetMapping("/getbyid/{userId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public User getByUserId(@PathVariable int userId) {
    	
        return service.getByUserId(userId);
    }

    @DeleteMapping("/deletebyid/{userId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public String deleteByUserId(@PathVariable int userId) {
        log.info("Received request to delete user with ID: {}", userId);

        return service.deleteByUserId(userId);
    }

    @GetMapping("/getbyemail/{email}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<User> getByEmail(@PathVariable String email) {
        log.info("Received request to fetch user by email: {}", email);

        return service.getByEmail(email);
    }

    @GetMapping("/getbyname/{name}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public User getByName(@PathVariable String name) {
        log.info("Received request to fetch users by name: {}", name);

        return service.getByName(name);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('Admin')")
    public List<User> getAllUsers() {
        log.info("Received request to fetch all users");

        return service.getAllUsers();
    }
    @GetMapping("/getbynamestartingwith/{name}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<User> getByNameStartingWith(@PathVariable String name){
    	return service.getByNameStartingWith(name);
    }
}
