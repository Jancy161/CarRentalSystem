package com.hexaware.carrentalsystems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.carrentalsystems.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
    
	@Query("SELECT u FROM user u WHERE u.name LIKE CONCAT(?1, '%')")
    List<User> findByNameStartingwith(String name);
}
