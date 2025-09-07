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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ReservationServiceImp implements IReservationService {

    @Autowired
    private IReservationRepository repo;
    
    @Autowired
    IUserRepository UserRepo;

    @Autowired
    ICarRepository CarRepo;

    @Override
    //add new reservation
    public Reservation addReservation(ReservationDto dto) {
        log.info("Adding new reservation: {}", dto);

        User user = UserRepo.findById(dto.getUserId()).orElseThrow(() -> {
            log.error("User not found with ID: {}", dto.getUserId());
            return new UserNotFoundException("User not found");
        });
        Car car = CarRepo.findById(dto.getCarId()).orElseThrow(() -> {
            log.error("Car not found with ID: {}", dto.getCarId());
            return new CarNotFoundException("Car not found");
        });
//status check 
        // ✅ Check if car is already booked
        if ("BOOKED".equalsIgnoreCase(car.getAvailability())) {
            throw new IllegalStateException("Car is already booked!");
        }

        // ✅ Mark car as booked
        car.setAvailability("BOOKED");
        CarRepo.save(car);
//status check
        Reservation res = new Reservation();
        res.setReservationId(dto.getReservationId());
        res.setUser(user);
        res.setCar(car);
       // res.setCar(dto.getCarModel());
        res.setPickupDate(dto.getPickupDate());
        res.setDropoffDate(dto.getDropoffDate());
        res.setTotalAmount(dto.getTotalAmount());
        res.setStatus(dto.getStatus());

        return repo.save(res);
    }


    @Override
    public Reservation updateReservation(Reservation reservation) {
        log.info("Updating reservation with ID: {}", reservation.getReservationId());

        return repo.save(reservation);
    }

	/*
	 * @Override // get reservation by id // parameters were reservation id public
	 * Reservation getReservationByUserId(int userId) {
	 * log.info("Fetching reservation with ID: {}", userId);
	 * 
	 * return repo.findById(userId) .orElseThrow(() -> new
	 * ReservationNotFoundException("Reservation not found with User ID: " +
	 * userId)); }
	 */

    @Override
    public List<Reservation> getAllReservations() {
        log.info("Fetching all reservations");

        return repo.findAll();
    }

    @Override
    public String deleteByReservationId(int reservationId) {
        log.info("Deleting reservation with ID: {}", reservationId);

    	if (repo.existsById(reservationId)) {
    	 repo.deleteById(reservationId);
    	 return "Reservation deleted";
    	 } else {
    	        throw  new ReservationNotFoundException("Reservation not found with ID: " + reservationId);
    }
    }


	@Override
	public List<Reservation> getByReservationGreaterThan(int totalAmount) {
		
		return repo.findByReservationGreaterThan(totalAmount);
	}  
	
	//modify , cancel
	
	@Override
	public List<Reservation> getReservationsByUserId(int userId) {
	    User user = UserRepo.findById(userId)
	              .orElseThrow(() -> new UserNotFoundException("User not found"));
	    return repo.findByUser(user); // you’ll need this repo method
	}

	/*
	 * @Override public Reservation cancelReservation(int reservationId) {
	 * Reservation res = repo.findById(reservationId) .orElseThrow(() -> new
	 * ReservationNotFoundException("Not found")); res.setStatus("CANCELLED");
	 * return repo.save(res); }
	 */
	
	@Override
	public Reservation cancelReservation(int reservationId) {
	    Reservation reservation = repo.findById(reservationId)
	        .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

	    // ✅ Update reservation status
	    reservation.setStatus("CANCELLED");
	    repo.save(reservation);

	    // ✅ Free the car
	    Car car = reservation.getCar();
	    if (car != null) {
	        car.setAvailability("AVAILABLE");
	        CarRepo.save(car);
	    }

	    return reservation;
	}

}
   