package com.hexaware.carrentalsystems.service;

import java.util.List;

import com.hexaware.carrentalsystems.dto.UserDto;
import com.hexaware.carrentalsystems.entities.User;

public interface IUserService {
    public User addUser(UserDto dto);
    public User updateUser(User user);
    public User getByUserId(int userId);
    public String deleteByUserId(int userId);
    User getByEmail(String email);
    List<User> getByName(String name);
    List<User> getAllUsers();
}
