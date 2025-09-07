package com.hexaware.carrentalsystems.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.carrentalsystems.dto.FeedbackDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.Feedback;
import com.hexaware.carrentalsystems.entities.User;
import com.hexaware.carrentalsystems.repository.ICarRepository;
import com.hexaware.carrentalsystems.repository.IFeedbackRepository;
import com.hexaware.carrentalsystems.repository.IUserRepository;

@SpringBootTest
class FeedbackServiceImpTest {

    @Autowired
    private FeedbackServiceImp feedbackService;

    @Autowired
    private IFeedbackRepository feedbackRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ICarRepository carRepo;

    private User testUser;
    private Car testCar;

    @BeforeEach
    void setup() {
        // Clean database to avoid duplicates
        feedbackRepo.deleteAll();
        carRepo.deleteAll();
        userRepo.deleteAll();

        // Create a test user
        testUser = new User();
        testUser.setUserId(1);
        testUser.setName("Test User");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        userRepo.save(testUser);

        // Create a test car
        testCar = new Car();
        testCar.setCarId(1);
        testCar.setBrand("Toyota");
        testCar.setModel("Corolla");
        testCar.setAvailability("AVAILABLE");
        testCar.setPricePerDay(1000.0);
        carRepo.save(testCar);
    }

    @Test
    void testAddFeedback() {
        FeedbackDto dto = new FeedbackDto();
        dto.setFeedbackId(100);
        dto.setUserId(testUser.getUserId());
        dto.setCarId(testCar.getCarId());
        dto.setRating(5);
        dto.setComment("Excellent car!");

        Feedback saved = feedbackService.addFeedback(dto);
        assertNotNull(saved);
        assertEquals(100, saved.getFeedbackId());
        assertEquals(5, saved.getRating());
        assertEquals("Excellent car!", saved.getComment());
        assertEquals(testUser.getUserId(), saved.getUser().getUserId());
        assertEquals(testCar.getCarId(), saved.getCar().getCarId());
    }

    @Test
    void testGetFeedbackById() {
        // First add feedback
        FeedbackDto dto = new FeedbackDto();
        dto.setFeedbackId(101);
        dto.setUserId(testUser.getUserId());
        dto.setCarId(testCar.getCarId());
        dto.setRating(4);
        dto.setComment("Good car");
        feedbackService.addFeedback(dto);

        Feedback feedback = feedbackService.getFeedbackById(101);
        assertNotNull(feedback);
        assertEquals(101, feedback.getFeedbackId());
    }

    @Test
    void testGetAllFeedback() {
        FeedbackDto dto1 = new FeedbackDto();
        dto1.setFeedbackId(102);
        dto1.setUserId(testUser.getUserId());
        dto1.setCarId(testCar.getCarId());
        dto1.setRating(3);
        dto1.setComment("Average");
        feedbackService.addFeedback(dto1);

        FeedbackDto dto2 = new FeedbackDto();
        dto2.setFeedbackId(103);
        dto2.setUserId(testUser.getUserId());
        dto2.setCarId(testCar.getCarId());
        dto2.setRating(5);
        dto2.setComment("Excellent!");
        feedbackService.addFeedback(dto2);

        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        assertEquals(2, feedbacks.size());
    }

    @Test
    void testDeleteFeedback() {
        FeedbackDto dto = new FeedbackDto();
        dto.setFeedbackId(104);
        dto.setUserId(testUser.getUserId());
        dto.setCarId(testCar.getCarId());
        dto.setRating(2);
        dto.setComment("Not good");
        feedbackService.addFeedback(dto);

        String message = feedbackService.deleteFeedback(104);
        assertEquals("Feedback deleted", message);
        assertThrows(Exception.class, () -> feedbackService.getFeedbackById(104));
    }

    @Test
    void testGetFeedbacksOrderByRating() {
        FeedbackDto dto1 = new FeedbackDto();
        dto1.setFeedbackId(105);
        dto1.setUserId(testUser.getUserId());
        dto1.setCarId(testCar.getCarId());
        dto1.setRating(3);
        dto1.setComment("Average");
        feedbackService.addFeedback(dto1);

        FeedbackDto dto2 = new FeedbackDto();
        dto2.setFeedbackId(106);
        dto2.setUserId(testUser.getUserId());
        dto2.setCarId(testCar.getCarId());
        dto2.setRating(5);
        dto2.setComment("Excellent!");
        feedbackService.addFeedback(dto2);

        List<Feedback> sortedFeedbacks = feedbackService.getFeedbacksOrderByRating();
        assertEquals(2, sortedFeedbacks.size());
        assertTrue(sortedFeedbacks.get(0).getRating() >= sortedFeedbacks.get(1).getRating());
    }
}
