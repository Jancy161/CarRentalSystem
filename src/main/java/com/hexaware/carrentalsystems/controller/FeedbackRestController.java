package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public Feedback addFeedback(@RequestBody @Valid FeedbackDto dto) {
        log.info("Received request to add feedback: {}", dto);

        return service.addFeedback(dto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public List<Feedback> getAll() {
        log.info("Received request to fetch all feedback");

        return service.getAllFeedback();
    }

    @GetMapping("/{feedbackId}")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    public Feedback getById(@PathVariable int feedbackId) {
        log.info("Received request to fetch feedback with ID: {}", feedbackId);

        return service.getFeedbackById(feedbackId);
    }

    @DeleteMapping("/delete/{feedbackId}")
    @PreAuthorize("hasAuthority('Admin')")
    public String delete(@PathVariable int feedbackId) {
        log.info("Received request to delete feedback with ID: {}", feedbackId);

        return service.deleteFeedback(feedbackId);
    }
    
    @GetMapping("/getorderbyrating")
    @PreAuthorize("hasAnyAuthority('Admin','User')")
    
    public List<Feedback> getFeedbacksOrderByRating(){
    	log.info("Ordering feedbacks by rating...");
    	return service.getFeedbacksOrderByRating();
    }
}
