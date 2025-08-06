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

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    IUserService service;

    @PostMapping("/insert")
    public User addUser(@RequestBody @Valid UserDto dto) {
        return service.addUser(dto);
    }
   
    

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @GetMapping("/getbyid/{userId}")
    public User getByUserId(@PathVariable int userId) {
        return service.getByUserId(userId);
    }

    @DeleteMapping("/deletebyid/{userId}")
    public String deleteByUserId(@PathVariable int userId) {
        return service.deleteByUserId(userId);
    }

    @GetMapping("/getbyemail/{email}")
    public User getByEmail(@PathVariable String email) {
        return service.getByEmail(email);
    }

    @GetMapping("/getbyname/{name}")
    public List<User> getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/getall")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
}
