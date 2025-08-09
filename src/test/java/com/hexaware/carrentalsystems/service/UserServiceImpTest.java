package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.hexaware.carrentalsystems.dto.UserDto;
import com.hexaware.carrentalsystems.entities.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // rolls back after each test to keep DB clean
public class UserServiceImpTest {

    @Autowired
    private UserServiceImp userService;

    private int savedUserId;

    @BeforeEach
    void setUp() {
        UserDto dto = new UserDto();
        dto.setUserId(100);
        dto.setName("Riya");
        dto.setEmail("Riya@example.com");
        dto.setPassword("Riya123");

        User saved = userService.addUser(dto);
        savedUserId = saved.getUserId();
    }

    @Test
    void testAddUser() {
        UserDto dto = new UserDto();
        dto.setUserId(101);
        dto.setName("John");
        dto.setEmail("john@example.com");
        dto.setPassword("pass123");

        User saved = userService.addUser(dto);
        assertNotNull(saved);
        assertEquals("John", saved.getName());
    }

    @Test
    void testGetByUserId() {
        User user = userService.getByUserId(savedUserId);
        assertNotNull(user);
        assertEquals("Riya", user.getName());
    }

    @Test
    void testGetByEmail() {
        List<User> users = userService.getByEmail("Riya@example.com");
        assertFalse(users.isEmpty());
        assertEquals("Riya", users.get(0).getName());
    }

    @Test
    void testGetByName() {
        User user = userService.getByName("Riya");
        assertNotNull(user);
        assertEquals("Riya", user.getName());
    }
}
