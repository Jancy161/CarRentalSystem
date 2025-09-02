package com.hexaware.carrentalsystems.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.hexaware.carrentalsystems.entities.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.name LIKE CONCAT(?1, '%')")
    List<User> findByNameStartingWith(String name);


	List<User> findByEmail(String email);

	User findByName(String name);
	
	
	/*
	 * @Query("SELECT u fROM User WHERE u.name = ?1") User findByName(String name);
	 * 
	 * @Query("SELECT u fROM User WHERE u.email = ?1") List<User> findByEmail(String
	 * email);
	 */
	
	
}
