package com.hexaware.carrentalsystems.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private int rating;
    private String comment;
    
    
    public Feedback() {}
    
    
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}


	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", user=" + user + ", car=" + car + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
    
    
	
    
}
