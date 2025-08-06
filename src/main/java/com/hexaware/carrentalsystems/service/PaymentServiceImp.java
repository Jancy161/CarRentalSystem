package com.hexaware.carrentalsystems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.entities.Payment;
import com.hexaware.carrentalsystems.repository.IPaymentRepository;
import com.hexaware.carrentalsystems.exceptions.PaymentNotFoundException;

@Service
public class PaymentServiceImp implements IPaymentService {

    @Autowired
    IPaymentRepository PaymentRepo;

    @Override
    public Payment addPayment(Payment payment) {
        return PaymentRepo.save(payment);
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        Optional<Payment> payment = PaymentRepo.findById(paymentId);
        if (payment.isPresent()) {
            return payment.get();
        } else {
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        return PaymentRepo.findAll();
    }

    @Override
    public String deletePayment(int paymentId) {
        if (PaymentRepo.existsById(paymentId)) {
        	PaymentRepo.deleteById(paymentId);
            return "Payment deleted successfully";
        } else {
            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }
}
