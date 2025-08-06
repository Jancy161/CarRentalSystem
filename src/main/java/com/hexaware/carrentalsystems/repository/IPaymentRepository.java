package com.hexaware.carrentalsystems.repository;
import com.hexaware.carrentalsystems.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {

}
