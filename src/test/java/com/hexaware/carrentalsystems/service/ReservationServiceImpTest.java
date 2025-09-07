package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.exceptions.ReservationNotFoundException;
import com.hexaware.carrentalsystems.repository.ICarRepository;
import com.hexaware.carrentalsystems.repository.IReservationRepository;
import com.hexaware.carrentalsystems.repository.IUserRepository;

@SpringBootTest
class ReservationServiceImpTest {

    @Autowired
    private ReservationServiceImp reservationService;

    @Autowired
    private IReservationRepository reservationRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ICarRepository carRepo;

    private static int uniqueReservationId = 1000;

    private User testUser;
    private Car testCar;

    @BeforeEach
    void setup() {
        reservationRepo.deleteAll();
        carRepo.deleteAll();
        userRepo.deleteAll();

        testUser = new User();
        testUser.setUserId(1);
        testUser.setName("Test User");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("pass123");
        userRepo.save(testUser);

        testCar = new Car();
        testCar.setCarId(1);
        testCar.setBrand("Toyota");
        testCar.setModel("Corolla");
        testCar.setPricePerDay(1000);
        testCar.setAvailability("AVAILABLE");
        carRepo.save(testCar);
    }

    private ReservationDto createReservationDto() {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(uniqueReservationId++);
        dto.setUserId(testUser.getUserId());
        dto.setCarId(testCar.getCarId());
        dto.setPickupDate(Date.valueOf(LocalDate.now()));
        dto.setDropoffDate(Date.valueOf(LocalDate.now().plusDays(2)));
        dto.setTotalAmount(2000);
        dto.setStatus("ACTIVE");
        return dto;
    }

    @Test
    void testAddReservation() {
        ReservationDto dto = createReservationDto();
        Reservation reservation = reservationService.addReservation(dto);

        assertNotNull(reservation);
        assertEquals("ACTIVE", reservation.getStatus());
        assertEquals(testUser.getUserId(), reservation.getUser().getUserId());
        assertEquals("BOOKED", reservation.getCar().getAvailability());
    }

	/*
	 * @Test void testGetAllReservations() { ReservationDto dto1 =
	 * createReservationDto(); ReservationDto dto2 = createReservationDto();
	 * 
	 * reservationService.addReservation(dto1);
	 * reservationService.addReservation(dto2);
	 * 
	 * List<Reservation> allReservations = reservationService.getAllReservations();
	 * assertTrue(allReservations.size() >= 2); }
	 */

    @Test
    void testDeleteReservation() {
        ReservationDto dto = createReservationDto();
        Reservation reservation = reservationService.addReservation(dto);

        String msg = reservationService.deleteByReservationId(reservation.getReservationId());
        assertEquals("Reservation deleted", msg);

        assertThrows(ReservationNotFoundException.class,
                () -> reservationService.deleteByReservationId(reservation.getReservationId()));
    }

    @Test
    void testCancelReservation() {
        ReservationDto dto = createReservationDto();
        Reservation reservation = reservationService.addReservation(dto);

        Reservation cancelled = reservationService.cancelReservation(reservation.getReservationId());
        assertEquals("CANCELLED", cancelled.getStatus());
        assertEquals("AVAILABLE", cancelled.getCar().getAvailability());
    }

	/*
	 * @Test void testGetReservationsByUserId() { ReservationDto dto1 =
	 * createReservationDto(); ReservationDto dto2 = createReservationDto();
	 * reservationService.addReservation(dto1);
	 * reservationService.addReservation(dto2);
	 * 
	 * List<Reservation> reservations =
	 * reservationService.getReservationsByUserId(testUser.getUserId());
	 * assertTrue(reservations.size() >= 2); }
	 */
}
