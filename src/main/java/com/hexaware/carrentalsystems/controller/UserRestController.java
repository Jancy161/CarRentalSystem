package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User addUser(@RequestBody @Valid UserDto dto) {
        log.info("Received request to add user: {}", dto);

        return service.addUser(dto);
    }
   
    

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        log.info("Received request to fetch user with ID: {}");

        return service.updateUser(user);
    }

    @GetMapping("/getbyid/{userId}")
    public User getByUserId(@PathVariable int userId) {
    	
        return service.getByUserId(userId);
    }

    @DeleteMapping("/deletebyid/{userId}")
    public String deleteByUserId(@PathVariable int userId) {
        log.info("Received request to delete user with ID: {}", userId);

        return service.deleteByUserId(userId);
    }

    @GetMapping("/getbyemail/{email}")
    public List<User> getByEmail(@PathVariable String email) {
        log.info("Received request to fetch user by email: {}", email);

        return service.getByEmail(email);
    }

    @GetMapping("/getbyname/{name}")
    public User getByName(@PathVariable String name) {
        log.info("Received request to fetch users by name: {}", name);

        return service.getByName(name);
    }

    @GetMapping("/getall")
    public List<User> getAllUsers() {
        log.info("Received request to fetch all users");

        return service.getAllUsers();
    }
    @GetMapping("/getbynamestartingwith/{name}")
    public List<User> getByNameStartingWith(@PathVariable String name){
    	return service.getByNameStartingWith(name);
    }
}
