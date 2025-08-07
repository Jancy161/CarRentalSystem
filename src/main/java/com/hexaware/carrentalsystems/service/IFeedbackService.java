package com.hexaware.carrentalsystems.service;

import java.util.List;

import com.hexaware.carrentalsystems.dto.FeedbackDto;
import com.hexaware.carrentalsystems.entities.Feedback;

public interface IFeedbackService {
    Feedback addFeedback(FeedbackDto dto);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(int feedbackId);
    String deleteFeedback(int feedbackId);
    
}

   