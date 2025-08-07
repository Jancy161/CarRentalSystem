package com.hexaware.carrentalsystems.service;

import java.util.List;

import com.hexaware.carrentalsystems.dto.PaymentDto;
import com.hexaware.carrentalsystems.entities.Payment;

public interface IPaymentService {
    public Payment addPayment(PaymentDto dto);
    public Payment getPaymentById(int paymentId);
    public List<Payment> getAllPayments();
    public String deletePayment(int paymentId);
}
