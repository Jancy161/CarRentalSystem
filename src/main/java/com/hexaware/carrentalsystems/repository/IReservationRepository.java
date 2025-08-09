package com.hexaware.carrentalsystems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.carrentalsystems.entities.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
	
	@Query("SELECT r fROM Reservation r WHERE r.totalAmount> ?1")
	List<Reservation> findByReservationGreaterThan(int totalAmount);
}
