package com.hexaware.carrentalsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.entities.Feedback;
import com.hexaware.carrentalsystems.service.IFeedbackService;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackRestController {

    @Autowired
    private IFeedbackService service;

    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return service.addFeedback(feedback);
    }

    @GetMapping("/all")
    public List<Feedback> getAll() {
        return service.getAllFeedback();
    }

    @GetMapping("/{id}")
    public Feedback getById(@PathVariable int id) {
        return service.getFeedbackById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        return service.deleteFeedback(id);
    }
}
