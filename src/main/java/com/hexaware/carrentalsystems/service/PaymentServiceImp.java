package com.hexaware.carrentalsystems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.dto.PaymentDto;
import com.hexaware.carrentalsystems.entities.Payment;
import com.hexaware.carrentalsystems.entities.Reservation;
import com.hexaware.carrentalsystems.repository.IPaymentRepository;
import com.hexaware.carrentalsystems.repository.IReservationRepository;

import lombok.extern.slf4j.Slf4j;

import com.hexaware.carrentalsystems.exceptions.PaymentNotFoundException;
import com.hexaware.carrentalsystems.exceptions.ReservationNotFoundException;
@Slf4j
@Service
public class PaymentServiceImp implements IPaymentService {

    @Autowired
    IPaymentRepository PaymentRepo;
    
    @Autowired
    IReservationRepository repo;

    @Override
    //add new payment
    public Payment addPayment(PaymentDto dto) {
        log.info("Adding new payment: {}", dto);

        Reservation reservation = repo.findById(dto.getReservationId())
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));

        Payment payment = new Payment();
        payment.setPaymentId(dto.getPaymentId());
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setStatus(dto.getStatus());
        payment.setReservation(reservation);

        return PaymentRepo.save(payment);
    }

    @Override
    //retrieve by id
    public Payment getPaymentById(int paymentId) {
        log.info("Fetching payment with ID: {}", paymentId);

        Optional<Payment> payment = PaymentRepo.findById(paymentId);
        if (payment.isPresent()) {
            log.debug("Payment found: {}", payment.get());

            return payment.get();
        } else {
            log.error("Payment not found with ID: {}", paymentId);

            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

    @Override
    //get all 
    public List<Payment> getAllPayments() {
        log.info("Fetching all payments");

        return PaymentRepo.findAll();
    }

    @Override
    public String deletePayment(int paymentId) {
        log.info("Deleting payment with ID: {}", paymentId);

        if (PaymentRepo.existsById(paymentId)) {
        	PaymentRepo.deleteById(paymentId);
            log.debug("Payment deleted successfully with ID: {}", paymentId);

            return "Payment deleted successfully";
        } else {
            log.error("Payment not found with ID: {}", paymentId);

            throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
        }
    }

	@Override
	public List<Payment> getByMethodAndStatus(String method, String status) {
		
		return PaymentRepo.findByMethodAndStatus( method, status);
	}
    
    
}
