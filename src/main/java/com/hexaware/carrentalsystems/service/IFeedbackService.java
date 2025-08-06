package com.hexaware.carrentalsystems.service;

import java.util.List;
import com.hexaware.carrentalsystems.entities.Feedback;

public interface IFeedbackService {
    Feedback addFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(int id);
    String deleteFeedback(int id);
}
