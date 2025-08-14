package com.hexaware.carrentalsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.exceptions.UserNotFoundException;
import com.hexaware.carrentalsystems.repository.IUserRepository;


import lombok.extern.slf4j.Slf4j;

import com.hexaware.carrentalsystems.dto.UserDto;
@Slf4j
@Service
public class UserServiceImp implements IUserService {
//injecting
    @Autowired
    private IUserRepository UserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override 
    //add new user
    public User addUser(UserDto dto) {
    	
        log.info("Adding new user: {}", dto);

		User user = new User();
		
		user.setUserId(dto.getUserId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
			
		 user.setPassword(passwordEncoder.encode(user.getPassword()));	
			
	
		return UserRepo.save(user);
	}
	/*
	 * public User addUser(User user) {
	 * 
	 * return UserRepo.save(user); }
	 */

   
    
    @Override
    //update user details
    public User updateUser(int userId,UserDto dto) {
    	log.info("Updating user with ID: {}");
       User existingUser = UserRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        
       existingUser.setUserId(dto.getUserId());
       existingUser.setName(dto.getName());
       existingUser.setEmail(dto.getEmail());
       existingUser.setPassword(dto.getPassword());
       existingUser.setRole(dto.getRole());
        
        return UserRepo.save(existingUser);
    }

    @Override
    //get user by id
    public User getByUserId(int userId) {
        log.info("Fetching user by ID: {}", userId);

        return UserRepo.findById(userId)
                   .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public String deleteByUserId(int userId) {
        log.info("Deleting user with ID: {}", userId);

    	UserRepo.deleteById(userId);
        return "User deleted successfully";
    }

    @Override
    public List <User> getByEmail(String email) {
        log.info("Fetching user by email: {}", email);

        return UserRepo.findByEmail(email);
                  
    }

    @Override
    public User getByName(String name) {
        log.info("Fetching users by name: {}", name);

        return UserRepo.findByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Fetching all users");

        return UserRepo.findAll();
    }

	@Override
	public List<User> getByNameStartingWith(String name) {
		
		return UserRepo.findByNameStartingWith(name);
	}
}
