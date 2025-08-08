package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.hexaware.carrentalsystems.dto.UserDto;
import com.hexaware.carrentalsystems.entities.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserServiceImp userService;

    @Test
    void testAddUser() {
        UserDto dto = new UserDto();
        dto.setUserId(10);
        dto.setName("Priya");
        dto.setEmail("priya@example.com");
        dto.setPassword("priya123");

        User saved = userService.addUser(dto);
        assertNotNull(saved);
        assertEquals("Priya", saved.getName());
    }

   

    @Test
    void testGetByUserId() {
        User user = userService.getByUserId(10); // assuming this user exists
        assertNotNull(user);
        assertEquals(10, user.getUserId());
    }

   

    @Test
    void testGetByEmail() {
        User user = userService.getByEmail("priya@example.com");
        assertNotNull(user);
        assertEquals("Priya", user.getName());
    }

    @Test
    void testGetByName() {
        List<User> users = userService.getByName("Priya");
        assertFalse(users.isEmpty());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    void testDeleteByUserId() {
        String result = userService.deleteByUserId(11); // This ID must exist
        assertEquals("User deleted successfully", result);
    }
}
