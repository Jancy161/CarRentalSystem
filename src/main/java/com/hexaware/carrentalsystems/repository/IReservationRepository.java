package com.hexaware.carrentalsystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hexaware.carrentalsystems.entities.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
}
