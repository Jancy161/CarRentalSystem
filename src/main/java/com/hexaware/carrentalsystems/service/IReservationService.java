package com.hexaware.carrentalsystems.service;

import java.util.List;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Reservation;

public interface IReservationService {
	Reservation addReservation(ReservationDto dto);
    Reservation updateReservation(Reservation reservation);
    Reservation getByReservationId(int reservationId);
    List<Reservation> getAllReservations();
    String deleteByReservationId(int reservationId);
}
