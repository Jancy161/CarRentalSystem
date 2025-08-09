package com.hexaware.carrentalsystems.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hexaware.carrentalsystems.entities.Feedback;
@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Integer>  {

	@Query("SELECT f FROM Feedback f ORDER BY f.rating DESC"  )
	List<Feedback> findFeedbacksOrderByRating ();
}
