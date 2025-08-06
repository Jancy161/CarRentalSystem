package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.entities.Payment;
import com.hexaware.carrentalsystems.service.IPaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    @Autowired
    IPaymentService service;

    @PostMapping("/insert")
    public Payment addPayment(@RequestBody Payment payment) {
        return service.addPayment(payment);
    }

    @GetMapping("/getbyid/{id}")
    public Payment getPaymentById(@PathVariable int id) {
        return service.getPaymentById(id);
    }

    @GetMapping("/getall")
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deletePayment(@PathVariable int id) {
        return service.deletePayment(id);
    }
}
