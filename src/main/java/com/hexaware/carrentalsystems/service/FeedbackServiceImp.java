
  package com.hexaware.carrentalsystems.service;
  
  import java.util.List; import java.util.Optional;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;
  
  import com.hexaware.carrentalsystems.dto.FeedbackDto; import
  com.hexaware.carrentalsystems.entities.Car; import
  com.hexaware.carrentalsystems.entities.Feedback; import
  com.hexaware.carrentalsystems.entities.User; import
  com.hexaware.carrentalsystems.exceptions.CarNotFoundException; import
  com.hexaware.carrentalsystems.exceptions.FeedbackNotFoundException; import
  com.hexaware.carrentalsystems.exceptions.UserNotFoundException; import
  com.hexaware.carrentalsystems.repository.ICarRepository; import
  com.hexaware.carrentalsystems.repository.IFeedbackRepository; import
  com.hexaware.carrentalsystems.repository.IUserRepository;
  
  import lombok.extern.slf4j.Slf4j;
  
  @Slf4j
  
  @Service public class FeedbackServiceImp implements IFeedbackService {
  
  @Autowired private IFeedbackRepository repo;
  
  @Autowired private IUserRepository UserRepo;
  
  @Autowired private ICarRepository CarRepo;
  
  @Override //adding feedback
  public Feedback addFeedback(FeedbackDto dto) {
  log.info("Adding feedback..."); Feedback feedback = new Feedback();
  feedback.setFeedbackId(dto.getFeedbackId());
  feedback.setRating(dto.getRating()); feedback.setComment(dto.getComment());
  
  User user = UserRepo.findById(dto.getUserId()) .orElseThrow(() -> new
  UserNotFoundException("User not found with ID: " + dto.getUserId())); Car car
  = CarRepo.findById(dto.getCarId()) .orElseThrow(() -> new
  CarNotFoundException("Car not found with ID: " + dto.getCarId()));
  
  feedback.setUser(user); feedback.setCar(car); return repo.save(feedback); }
  
  
  //method to fetch all feedback
  
  @Override public List<Feedback> getAllFeedback() {
  log.info("Fetching all feedback records");
  
  return repo.findAll(); }
  
  @Override //get by id 
  public Feedback getFeedbackById(int feedbackId) {
  log.info("Fetching feedback with ID: {}", feedbackId);
  
  Optional<Feedback> feedback = repo.findById(feedbackId); if
  (feedback.isPresent()) { return feedback.get(); } else { throw new
  FeedbackNotFoundException("Feedback not found with id: " + feedbackId); } }
  
  @Override
  public String deleteFeedback(int feedbackId) {
  log.info("Deleting feedback with ID: {}", feedbackId);
  
  if (repo.existsById(feedbackId)) { repo.deleteById(feedbackId); return
  "Feedback deleted"; } else { throw new
  FeedbackNotFoundException("Feedback not found with id: " + feedbackId); } }
  
  @Override
  public List<Feedback> getFeedbacksOrderByRating() { return
  repo.findFeedbacksOrderByRating();
  
  }
  
  
  
  }
  
 
