package com.hexaware.carrentalsystems.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "reservation_id")
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
    
    public Payment() {}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", reservation=" + reservation + ", amount=" + amount + ", method="
				+ method + ", status=" + status + "]";
	}
    
    
}
