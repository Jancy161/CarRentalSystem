package com.hexaware.carrentalsystems.repository;
import com.hexaware.carrentalsystems.entities.Payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
	
	@Query("SELECT p FROM Payment p WHERE p.method=?1 AND p.status= ?2")
	
	List<Payment> findByMethodAndStatus(String method, String Status);
}
