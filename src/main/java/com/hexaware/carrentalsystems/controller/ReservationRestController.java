package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.service.IReservationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    @Autowired
    private IReservationService service;

    @PostMapping("/add")
    public Reservation add(@RequestBody @Valid ReservationDto dto) {
        log.info("Received request to add reservation: {}", dto);

        return service.addReservation(dto);
    }

    @PutMapping("/update")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        log.info("Received request to update reservation with ID: {}", reservation.getReservationId());

        return service.updateReservation(reservation);
    }

    @GetMapping("/getbyid/{reservationId}")
    public Reservation getById(@PathVariable int reservationId) {
        log.info("Received request to fetch reservation with ID: {}", reservationId);

        return service.getByReservationId(reservationId);
    }

    @DeleteMapping("/deletebyid/{reservationId}")
    public String deleteById(@PathVariable int reservationId) {
        log.info("Received request to delete reservation with ID: {}", reservationId);

        return service.deleteByReservationId(reservationId);
    }

    @GetMapping("/getall")
    public List<Reservation> getAllReservations() {
        log.info("Received request to fetch all reservations");

        return service.getAllReservations();
    }
    
    @GetMapping("/getresgreaterthan/{totalAmount}")
    public List<Reservation> getByReservationGreaterThan(@PathVariable int totalAmount){
    	
    	return service.getByReservationGreaterThan(totalAmount);
    }
}
