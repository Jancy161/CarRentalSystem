package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.carrentalsystems.dto.ReservationDto;
import com.hexaware.carrentalsystems.entities.Reservation;

@SpringBootTest
class ReservationServiceImpTest {

    @Autowired
    private ReservationServiceImp service;

    @Test
    void testAddReservation_ValidData() {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(500);
        dto.setUserId(2);
        dto.setCarId(7);
        dto.setPickupDate(Date.valueOf("2025-08-10"));
        dto.setDropoffDate(Date.valueOf("2025-08-12"));
        dto.setTotalAmount(5400); // should ideally be calculated
        dto.setStatus("ACTIVE");

        Reservation result = service.addReservation(dto);
        assertNotNull(result);
        assertEquals(500, result.getReservationId());
    }

    @Test
    void testAddReservation_InvalidUserId() {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(501);
        dto.setUserId(999); // invalid
        dto.setCarId(7);
        dto.setPickupDate(Date.valueOf("2025-08-10"));
        dto.setDropoffDate(Date.valueOf("2025-08-12"));
        dto.setTotalAmount(5400);
        dto.setStatus("ACTIVE");

        Exception ex = assertThrows(RuntimeException.class, () -> service.addReservation(dto));
        assertTrue(ex.getMessage().contains("User not found"));
    }


   
    @Test
    void testGetByReservationId_Valid() {
        Reservation res = service.getByReservationId(2);
        assertNotNull(res);
        assertEquals(2, res.getReservationId());
    }

   

    @Test
    void testGetAllReservations() {
        List<Reservation> all = service.getAllReservations();
        assertNotNull(all);
        assertTrue(all.size() > 0);
    }

    @Test
    void testDeleteReservation_Success() {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(800);
        dto.setUserId(2);
        dto.setCarId(7);
        dto.setPickupDate(Date.valueOf("2025-08-10"));
        dto.setDropoffDate(Date.valueOf("2025-08-11"));
        dto.setTotalAmount(2700);
        dto.setStatus("ACTIVE");
        service.addReservation(dto);

        String msg = service.deleteByReservationId(800);
        assertEquals("Reservation deleted", msg);
    }

   
}
