package com.hexaware.carrentalsystems.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.entities.Feedback;
import com.hexaware.carrentalsystems.exceptions.FeedbackNotFoundException;
import com.hexaware.carrentalsystems.repository.IFeedbackRepository;

@Service
public class FeedbackServiceImp implements IFeedbackService {

    @Autowired
    private IFeedbackRepository repo;

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return repo.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return repo.findAll();
    }

    @Override
    public Feedback getFeedbackById(int id) {
        Optional<Feedback> feedback = repo.findById(id);
        if (feedback.isPresent()) {
            return feedback.get();
        } else {
            throw new FeedbackNotFoundException("Feedback not found with id: " + id);
        }
    }

    @Override
    public String deleteFeedback(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Feedback deleted";
        } else {
            throw new FeedbackNotFoundException("Feedback not found with id: " + id);
        }
    }
}
