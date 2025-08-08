package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.hexaware.carrentalsystems.dto.PaymentDto;
import com.hexaware.carrentalsystems.entities.Payment;
import com.hexaware.carrentalsystems.entities.Payment.Method;
import com.hexaware.carrentalsystems.entities.Payment.Status;
import com.hexaware.carrentalsystems.exceptions.PaymentNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceImpTest {

    @Autowired
    private PaymentServiceImp service;

   
   

    @Test
    void testGetPaymentById_Invalid() {
        Exception ex = assertThrows(PaymentNotFoundException.class, () -> service.getPaymentById(999));
        assertTrue(ex.getMessage().contains("not found"));
    }

    @Test
    void testGetAllPayments_NotEmpty() {
        List<Payment> payments = service.getAllPayments();
        assertNotNull(payments);
        assertTrue(payments.size() > 0);
    }

    @Test
    void testDeletePayment_Valid() {
        PaymentDto dto = new PaymentDto();
        dto.setPaymentId(604);
        dto.setReservationId(2);
        dto.setAmount(2000);
        dto.setMethod(Method.UPI);
        dto.setStatus(Status.SUCCESS);
        service.addPayment(dto);

        String result = service.deletePayment(604);
        assertEquals("Payment deleted successfully", result);
    }

    @Test
    void testDeletePayment_Invalid() {
        Exception ex = assertThrows(PaymentNotFoundException.class, () -> service.deletePayment(9999));
        assertTrue(ex.getMessage().contains("not found"));
    }
}
