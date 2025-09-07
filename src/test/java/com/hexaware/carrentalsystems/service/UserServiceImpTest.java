package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hexaware.carrentalsystems.dto.UserDto;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.exceptions.UserNotFoundException;
import com.hexaware.carrentalsystems.repository.IUserRepository;

@SpringBootTest
class UserServiceImpTest {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static int uniqueUserId = 1000;

    @BeforeEach
    void setup() {
        // Clean database before each test to avoid duplicates
        userRepo.deleteAll();
    }

    private UserDto createUserDto() {
        UserDto dto = new UserDto();
        dto.setUserId(uniqueUserId++);
        dto.setName("Test User");
        dto.setEmail("testuser" + uniqueUserId + "@example.com");
        dto.setPassword("password123");
        dto.setRole("USER");
        return dto;
    }

    @Test
    void testAddUser() {
        UserDto dto = createUserDto();
        User user = userService.addUser(dto);

        assertNotNull(user);
        assertEquals(dto.getName(), user.getName());
        assertEquals(dto.getEmail(), user.getEmail());
        assertTrue(passwordEncoder.matches("password123", user.getPassword()));
    }

    @Test
    void testGetByUserId() {
        UserDto dto = createUserDto();
        User saved = userService.addUser(dto);

        User fetched = userService.getByUserId(saved.getUserId());
        assertNotNull(fetched);
        assertEquals(saved.getUserId(), fetched.getUserId());
    }

    @Test
    void testUpdateUser() {
        UserDto dto = createUserDto();
        User saved = userService.addUser(dto);

        dto.setName("Updated Name");
        dto.setEmail("updated" + saved.getUserId() + "@example.com");
        User updated = userService.updateUser(saved.getUserId(), dto);

        assertEquals("Updated Name", updated.getName());
        assertEquals("updated" + saved.getUserId() + "@example.com", updated.getEmail());
    }

    @Test
    void testDeleteByUserId() {
        UserDto dto = createUserDto();
        User saved = userService.addUser(dto);

        String msg = userService.deleteByUserId(saved.getUserId());
        assertEquals("User deleted successfully", msg);

        assertThrows(UserNotFoundException.class, () -> userService.getByUserId(saved.getUserId()));
    }

    @Test
    void testGetByEmail() {
        UserDto dto = createUserDto();
        User saved = userService.addUser(dto);

        List<User> users = userService.getByEmail(saved.getEmail());
        assertFalse(users.isEmpty());
        assertEquals(saved.getEmail(), users.get(0).getEmail());
    }

    @Test
    void testGetByName() {
        UserDto dto = createUserDto();
        User saved = userService.addUser(dto);

        User fetched = userService.getByName(saved.getName());
        assertNotNull(fetched);
        assertEquals(saved.getName(), fetched.getName());
    }

    @Test
    void testGetAllUsers() {
        UserDto dto1 = createUserDto();
        UserDto dto2 = createUserDto();

        userService.addUser(dto1);
        userService.addUser(dto2);

        List<User> allUsers = userService.getAllUsers();
        assertTrue(allUsers.size() >= 2);
    }

    @Test
    void testGetByNameStartingWith() {
        UserDto dto1 = createUserDto();
        dto1.setName("Alice");
        UserDto dto2 = createUserDto();
        dto2.setName("Alex");

        userService.addUser(dto1);
        userService.addUser(dto2);

        List<User> users = userService.getByNameStartingWith("Al");
        assertEquals(2, users.size());
    }
}
