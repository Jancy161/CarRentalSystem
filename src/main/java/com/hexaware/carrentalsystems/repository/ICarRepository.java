package com.hexaware.carrentalsystems.repository;
import com.hexaware.carrentalsystems.entities.Car;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ICarRepository extends JpaRepository<Car, Integer> {
	 List<Car> findByBrand(String brand);
	    
	    List<Car> findByAvailability(String availability);

	    List<Car> findByPricePerDayLessThan(double price);

	    @Query("SELECT c FROM Car c WHERE c.pricePerDay < ?1 ORDER BY c.pricePerDay")
	    List<Car> findAffordableCarsSortedByPrice(double price);
	}