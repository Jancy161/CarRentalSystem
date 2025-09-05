package com.hexaware.carrentalsystems.service;

import java.util.List;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Reservation;

public interface IReservationService {
	Reservation addReservation(ReservationDto dto);
    Reservation updateReservation(Reservation reservation);
    //Reservation getReservationByUserId(int userId);//reservationId
    List<Reservation> getAllReservations();
    String deleteByReservationId(int reservationId);
    List<Reservation> getByReservationGreaterThan( int totalAmount);
    
    //modify and cancel
    List<Reservation> getReservationsByUserId(int userId);
    //List<ReservationDto> getReservationsByUserId(int userId);  // ✅ changed
    Reservation cancelReservation(int reservationId);

}
