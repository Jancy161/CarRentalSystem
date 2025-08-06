package com.hexaware.carrentalsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.exceptions.UserNotFoundException;
import com.hexaware.carrentalsystems.repository.IUserRepository;
import com.hexaware.carrentalsystems.dto.UserDto;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private IUserRepository UserRepo;
    
    @Override 
    public User addUser(UserDto dto) {
		
		User user = new User();
		
		user.setUserId(dto.getUserId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
			
			
			
	
		return UserRepo.save(user);
	}

    public User addUser(User user) {
        return UserRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        return UserRepo.save(user);
    }

    @Override
    public User getByUserId(int userId) {
        return UserRepo.findById(userId)
                   .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public String deleteByUserId(int userId) {
    	UserRepo.deleteById(userId);
        return "User deleted successfully";
    }

    @Override
    public User getByEmail(String email) {
        return UserRepo.findByEmail(email)
                   .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Override
    public List<User> getByName(String name) {
        return UserRepo.findByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return UserRepo.findAll();
    }
}
