package com.hexaware.carrentalsystems.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.carrentalsystems.entities.Feedback;
@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer>  {

}
