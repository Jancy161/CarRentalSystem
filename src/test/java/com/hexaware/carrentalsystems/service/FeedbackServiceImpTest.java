package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.hexaware.carrentalsystems.dto.FeedbackDto;
import com.hexaware.carrentalsystems.entities.Feedback;
import com.hexaware.carrentalsystems.exceptions.FeedbackNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FeedbackServiceImpTest {

    @Autowired
    private FeedbackServiceImp service;

    @Test
    void testAddFeedback_Valid() {
        FeedbackDto dto = new FeedbackDto();
        dto.setFeedbackId(201);
        dto.setRating(4);
        dto.setComment("Nice ride.");
        dto.setUserId(2); // Ensure this user exists
        dto.setCarId(7);  // Ensure this car exists

        Feedback feedback = service.addFeedback(dto);
        assertNotNull(feedback);
        assertEquals(4, feedback.getRating());
    }

    @Test
    void testAddFeedback_InvalidUser() {
        FeedbackDto dto = new FeedbackDto();
        dto.setFeedbackId(202);
        dto.setRating(3);
        dto.setComment("Okay ride.");
        dto.setUserId(9999); // Invalid user
        dto.setCarId(7);

        Exception ex = assertThrows(RuntimeException.class, () -> service.addFeedback(dto));
        assertTrue(ex.getMessage().contains("User not found"));
    }

    @Test
    void testGetAllFeedback_NotEmpty() {
        List<Feedback> feedbackList = service.getAllFeedback();
        assertNotNull(feedbackList);
        assertTrue(feedbackList.size() > 0);
    }

    @Test
    void testGetFeedbackById_Valid() {
        FeedbackDto dto = new FeedbackDto();
        dto.setFeedbackId(203);
        dto.setRating(5);
        dto.setComment("Amazing experience!");
        dto.setUserId(2);
        dto.setCarId(7);
        service.addFeedback(dto);

        Feedback f = service.getFeedbackById(203);
        assertNotNull(f);
        assertEquals(203, f.getFeedbackId());
    }

   


}
