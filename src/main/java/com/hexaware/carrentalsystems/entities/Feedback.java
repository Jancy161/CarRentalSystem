package com.hexaware.carrentalsystems.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-feedback")
    private User user;


    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference(value = "car-feedback")
    private Car car;

    private int rating;
    private String comment;
    
   
   
}
