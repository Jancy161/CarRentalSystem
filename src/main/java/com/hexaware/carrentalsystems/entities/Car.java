package com.hexaware.carrentalsystems.entities;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
public class Car {
    @Id

    private int carId;

	private String brand;
    private String model;
    private double pricePerDay;
    
    @Column(nullable = false)
    private String availability;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "car-reservation")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "car-feedback")
    private List<Feedback> feedbacks;

    

   }
