package com.hexaware.carrentalsystems.entities;
import java.sql.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hexaware.carrentalsystems.entities.Reservation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-reservation")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-reservation")
    private Car car;

    private Date pickupDate;
    private Date dropoffDate;
    private double totalAmount;


    private String status;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "reservation-payment")
    private Payment payment;

	/*
	 * public enum Status { ACTIVE, CANCELLED, COMPLETED }
	 */
    
   
}