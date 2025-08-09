package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.dto.FeedbackDto;
import com.hexaware.carrentalsystems.entities.Feedback;
import com.hexaware.carrentalsystems.service.IFeedbackService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api/feedback")
public class FeedbackRestController {

    @Autowired
    private IFeedbackService service;

    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody @Valid FeedbackDto dto) {
        log.info("Received request to add feedback: {}", dto);

        return service.addFeedback(dto);
    }

    @GetMapping("/all")
    public List<Feedback> getAll() {
        log.info("Received request to fetch all feedback");

        return service.getAllFeedback();
    }

    @GetMapping("/{feedbackId}")
    public Feedback getById(@PathVariable int feedbackId) {
        log.info("Received request to fetch feedback with ID: {}", feedbackId);

        return service.getFeedbackById(feedbackId);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public String delete(@PathVariable int feedbackId) {
        log.info("Received request to delete feedback with ID: {}", feedbackId);

        return service.deleteFeedback(feedbackId);
    }
    
    @GetMapping("/getorderbyrating")
    
    public List<Feedback> getFeedbacksOrderByRating(){
    	log.info("Ordering feedbacks by rating...");
    	return service.getFeedbacksOrderByRating();
    }
}
