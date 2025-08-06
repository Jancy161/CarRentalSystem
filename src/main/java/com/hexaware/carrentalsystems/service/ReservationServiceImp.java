package com.hexaware.carrentalsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.exceptions.ReservationNotFoundException;
import com.hexaware.carrentalsystems.repository.IReservationRepository;

@Service
public class ReservationServiceImp implements IReservationService {

    @Autowired
    private IReservationRepository repo;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return repo.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return repo.save(reservation);
    }

    @Override
    public Reservation getByReservationId(int reservationId) {
        return repo.findById(reservationId)
                   .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with ID: " + reservationId));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return repo.findAll();
    }

    @Override
    public String deleteByReservationId(int reservationId) {
        repo.deleteById(reservationId);
        return "Reservation deleted successfully";
    }
}
