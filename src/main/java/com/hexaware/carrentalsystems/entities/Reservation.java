package com.hexaware.carrentalsystems.entities;
import java.sql.*;

import com.hexaware.carrentalsystems.entities.Reservation;
import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private Date pickupDate;
    private Date dropoffDate;
    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Payment payment;
    
    public enum Status {
        ACTIVE,
        CANCELLED,
        COMPLETED
    }
    
    public Reservation() {}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getDropoffDate() {
		return dropoffDate;
	}

	public void setDropoffDate(Date dropoffDate) {
		this.dropoffDate = dropoffDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", user=" + user + ", car=" + car + ", pickupDate="
				+ pickupDate + ", dropoffDate=" + dropoffDate + ", totalAmount=" + totalAmount + ", status=" + status
				+ ", payment=" + payment + "]";
	}
    
    
}