package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.carrentalsystems.dto.PaymentDto;
import com.hexaware.carrentalsystems.entities.Payment;
import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.repository.IPaymentRepository;
import com.hexaware.carrentalsystems.repository.IReservationRepository;
import com.hexaware.carrentalsystems.repository.IUserRepository;
import com.hexaware.carrentalsystems.repository.ICarRepository;
import com.hexaware.carrentalsystems.exceptions.PaymentNotFoundException;

@SpringBootTest
class PaymentServiceImpTest {

    @Autowired
    private PaymentServiceImp paymentService;

    @Autowired
    private IPaymentRepository paymentRepo;

    @Autowired
    private IReservationRepository reservationRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ICarRepository carRepo;

    private static int uniqueReservationId = 1000;
    private static int uniquePaymentId = 2000;

    private User testUser;
    private Car testCar;

    @BeforeEach
    void setup() {
        paymentRepo.deleteAll();
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

    private Reservation createReservation() {
        Reservation reservation = new Reservation();
        reservation.setReservationId(uniqueReservationId++);
        reservation.setUser(testUser);
        reservation.setCar(testCar);
        reservation.setTotalAmount(1000);
        reservation.setPickupDate(Date.valueOf("2025-08-10"));
        reservation.setDropoffDate(Date.valueOf("2025-08-11"));
        reservation.setStatus("CONFIRMED");
        return reservationRepo.save(reservation);
    }

    @Test
    void testAddPayment() {
        Reservation res = createReservation();

        PaymentDto dto = new PaymentDto();
        dto.setPaymentId(uniquePaymentId++);
        dto.setReservationId(res.getReservationId());
        dto.setAmount(2500);
        dto.setMethod("CARD");
        dto.setStatus("SUCCESS");

        Payment payment = paymentService.addPayment(dto);

        assertNotNull(payment);
        assertEquals(2500, payment.getAmount());
        assertEquals("CARD", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testGetPaymentById() {
        Reservation res = createReservation();

        PaymentDto dto = new PaymentDto();
        dto.setPaymentId(uniquePaymentId++);
        dto.setReservationId(res.getReservationId());
        dto.setAmount(1500);
        dto.setMethod("UPI");
        dto.setStatus("SUCCESS");

        Payment saved = paymentService.addPayment(dto);

        Payment fetched = paymentService.getPaymentById(saved.getPaymentId());

        assertNotNull(fetched);
        assertEquals(saved.getPaymentId(), fetched.getPaymentId());
    }

    @Test
    void testGetAllPayments() {
        Reservation res1 = createReservation();
        Reservation res2 = createReservation();

        PaymentDto dto1 = new PaymentDto();
        dto1.setPaymentId(uniquePaymentId++);
        dto1.setReservationId(res1.getReservationId());
        dto1.setAmount(2000);
        dto1.setMethod("CARD");
        dto1.setStatus("FAILED");
        paymentService.addPayment(dto1);

        PaymentDto dto2 = new PaymentDto();
        dto2.setPaymentId(uniquePaymentId++);
        dto2.setReservationId(res2.getReservationId());
        dto2.setAmount(3000);
        dto2.setMethod("UPI");
        dto2.setStatus("SUCCESS");
        paymentService.addPayment(dto2);

        List<Payment> allPayments = paymentService.getAllPayments();
        assertTrue(allPayments.size() >= 2);
    }

	/*
	 * @Test void testDeletePayment() { Reservation res = createReservation();
	 * 
	 * PaymentDto dto = new PaymentDto(); dto.setPaymentId(uniquePaymentId++);
	 * dto.setReservationId(res.getReservationId()); dto.setAmount(1800);
	 * dto.setMethod("CARD"); dto.setStatus("SUCCESS");
	 * 
	 * Payment payment = paymentService.addPayment(dto);
	 * 
	 * String msg = paymentService.deletePayment(payment.getPaymentId());
	 * assertEquals("Payment deleted successfully", msg);
	 * 
	 * assertThrows(PaymentNotFoundException.class, () ->
	 * paymentService.getPaymentById(payment.getPaymentId())); }
	 */}
