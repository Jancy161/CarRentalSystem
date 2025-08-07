package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.dto.FeedbackDto;
import com.hexaware.carrentalsystems.entities.Feedback;
import com.hexaware.carrentalsystems.service.IFeedbackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackRestController {

    @Autowired
    private IFeedbackService service;

    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody @Valid FeedbackDto dto) {
        return service.addFeedback(dto);
    }

    @GetMapping("/all")
    public List<Feedback> getAll() {
        return service.getAllFeedback();
    }

    @GetMapping("/{feedbackId}")
    public Feedback getById(@PathVariable int feedbackId) {
        return service.getFeedbackById(feedbackId);
    }

    @DeleteMapping("/delete/{feedbackId}")
    public String delete(@PathVariable int feedbackId) {
        return service.deleteFeedback(feedbackId);
    }
}
