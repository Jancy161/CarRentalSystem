package com.hexaware.carrentalsystems.entities;
import jakarta.persistence.*;
import java.util.List;
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Availability availability;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    public enum Availability {
        AVAILABLE,
        BOOKED
    }

   }
