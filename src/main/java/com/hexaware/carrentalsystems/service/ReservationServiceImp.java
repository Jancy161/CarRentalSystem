package com.hexaware.carrentalsystems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.exceptions.CarNotFoundException;
import com.hexaware.carrentalsystems.exceptions.FeedbackNotFoundException;
import com.hexaware.carrentalsystems.exceptions.ReservationNotFoundException;
import com.hexaware.carrentalsystems.exceptions.UserNotFoundException;
import com.hexaware.carrentalsystems.repository.ICarRepository;
import com.hexaware.carrentalsystems.repository.IReservationRepository;
import com.hexaware.carrentalsystems.repository.IUserRepository;

@Service
public class ReservationServiceImp implements IReservationService {

    @Autowired
    private IReservationRepository repo;
    
    @Autowired
    IUserRepository UserRepo;

    @Autowired
    ICarRepository CarRepo;

    @Override
    public Reservation addReservation(ReservationDto dto) {
        User user = UserRepo.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Car car = CarRepo.findById(dto.getCarId()).orElseThrow(() -> new CarNotFoundException("Car not found"));

        Reservation res = new Reservation();
        res.setReservationId(dto.getReservationId());
        res.setUser(user);
        res.setCar(car);
        res.setPickupDate(dto.getPickupDate());
        res.setDropoffDate(dto.getDropoffDate());
        res.setTotalAmount(dto.getTotalAmount());
        res.setStatus(dto.getStatus());

        return repo.save(res);
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
    	if (repo.existsById(reservationId)) {
    	 repo.deleteById(reservationId);
    	 return "Reservation deleted";
    	 } else {
    	        throw  new ReservationNotFoundException("Reservation not found with ID: " + reservationId);
    }
    }  
}
   