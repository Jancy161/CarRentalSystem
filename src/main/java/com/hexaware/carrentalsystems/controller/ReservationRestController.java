package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.service.IReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    @Autowired
    private IReservationService service;

    @PostMapping("/add")
    public Reservation add(@RequestBody @Valid ReservationDto dto) {
        return service.addReservation(dto);
    }

    @PutMapping("/update")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return service.updateReservation(reservation);
    }

    @GetMapping("/getbyid/{reservationId}")
    public Reservation getById(@PathVariable int reservationId) {
        return service.getByReservationId(reservationId);
    }

    @DeleteMapping("/deletebyid/{reservationId}")
    public String deleteById(@PathVariable int reservationId) {
        return service.deleteByReservationId(reservationId);
    }

    @GetMapping("/getall")
    public List<Reservation> getAllReservations() {
        return service.getAllReservations();
    }
}
