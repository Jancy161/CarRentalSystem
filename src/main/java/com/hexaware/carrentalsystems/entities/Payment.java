package com.hexaware.carrentalsystems.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    @JsonBackReference(value = "reservation-payment")
    private Reservation reservation;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Method method;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Method {
        CARD,
        UPI
    }

    public enum Status {
        SUCCESS,
        FAILED
    }
    
    
}
