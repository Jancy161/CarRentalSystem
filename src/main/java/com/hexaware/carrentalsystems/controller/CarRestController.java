package com.hexaware.carrentalsystems.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.entities.Car;

import com.hexaware.carrentalsystems.service.ICarService;
import com.hexaware.carrentalsystems.dto.CarDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cars")
public class CarRestController {
	



	    @Autowired
	    ICarService service;

	    @PostMapping("/insert")
	    @PreAuthorize("hasAuthority('Admin')")
	    public Car addCar(@RequestBody @Valid CarDto dto) {
	    	 log.info("Received request to add new car: {}", dto);

	        return service.addCar(dto);
	    }
	    
			  

	    @PutMapping("/update")
	    @PreAuthorize("hasAuthority('Admin')")
	    public Car updateCar(@RequestBody Car car) {
	        log.info("Received request to update car with ID: {}", car.getCarId());

	        return service.updateCar(car);
	    }

	    @GetMapping("/getbyid/{carId}")
	    @PreAuthorize("hasAnyAuthority('Admin','User')")
	    public Car getByCarId(@PathVariable int carId) {
	        log.info("Received request to fetch car by ID: {}", carId);

	        return service.getByCarId(carId);
	    }
	   

	    @GetMapping("/getall")
	    @PreAuthorize("hasAnyAuthority('Admin','User')")
	    public List<Car> getAllCars() {
	        log.info("Fetching all cars");
	        return service.getAllCars();
	    }

	    @DeleteMapping("/deletebyid/{carId}")
	    @PreAuthorize("hasAuthority('Admin')")
	    public String deleteByCarId(@PathVariable int carId) {
	        log.info("Received request to delete car with ID: {}", carId);

	        return service.deleteByCarId(carId);
	    }

	    @GetMapping("/getbybrand/{brand}")
	    @PreAuthorize("hasAnyAuthority('Admin','User')")
	    public List<Car> getByBrand(@PathVariable String brand) {
	        log.info("Fetching cars by brand: {}", brand);

	    	
	        return service.getByBrand(brand);
	    }

	  

	    @GetMapping("/getbyprice/{price}")
	    @PreAuthorize("hasAnyAuthority('Admin','User')")
	    public List<Car> getByPriceLessThan(@PathVariable double price) {
	        log.info("Fetching cars with price less than {}", price);

	        return service.getByPriceLessThan(price);
	    }

	    @GetMapping("/getaffordable/orderbyprice/{price}")
	    @PreAuthorize("hasAnyAuthority('Admin','User')")
	    public List<Car> getAffordableSorted(@PathVariable double price) {
	        log.info("Fetching affordable cars sorted by price, price threshold: {}", price);

	        return service.findAffordableCarsSortedByPrice(price);
	    }
	}


