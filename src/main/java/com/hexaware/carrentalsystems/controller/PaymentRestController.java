package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.dto.PaymentDto;
import com.hexaware.carrentalsystems.entities.Payment;
import com.hexaware.carrentalsystems.service.IPaymentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    @Autowired
    IPaymentService service;

    @PostMapping("/insert")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public Payment addPayment(@RequestBody @Valid PaymentDto dto) {
        log.info("Received request to add payment: {}", dto);

        return service.addPayment(dto);
    }

    @GetMapping("/getbyid/{paymentId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public Payment getPaymentById(@PathVariable int paymentId) {
        log.info("Received request to get payment with ID: {}", paymentId);

        return service.getPaymentById(paymentId);
    }

    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('Admin')")

    public List<Payment> getAllPayments() {
        log.info("Received request to fetch all payments");

        return service.getAllPayments();
    }

    @DeleteMapping("/deletebyid/{paymentId}")
    @PreAuthorize("hasAuthority('Admin')")

    public String deletePayment(@PathVariable int paymentId) {
        log.info("Received request to delete payment with ID: {}", paymentId);

        return service.deletePayment(paymentId);
    }
    
    @GetMapping("/getbymethodandstatus/{method}/{status}")
    @PreAuthorize("hasAuthority('Admin')")

    
    	public List<Payment> getByMethodAndStatus(@PathVariable String method, @PathVariable String status){
    	return service.getByMethodAndStatus(method, status);
    }
}
