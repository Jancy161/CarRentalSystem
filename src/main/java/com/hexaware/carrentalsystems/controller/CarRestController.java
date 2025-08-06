package com.hexaware.carrentalsystems.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hexaware.carrentalsystems.entities.Car;

import com.hexaware.carrentalsystems.service.ICarService;
import com.hexaware.carrentalsystems.dto.CarDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarRestController {
	



	    @Autowired
	    ICarService service;

	    @PostMapping("/insert")
	    public Car addCar(@RequestBody @Valid CarDto dto) {
	        return service.addCar(dto);
	    }
	    
			  

	    @PutMapping("/update")
	    public Car updateCar(@RequestBody Car car) {
	        return service.updateCar(car);
	    }

	    @GetMapping("/getbyid/{carId}")
	    public Car getByCarId(@PathVariable int carId) {
	        return service.getByCarId(carId);
	    }

	    @GetMapping("/getall")
	    public List<Car> getAllCars() {
	        return service.getAllCars();
	    }

	    @DeleteMapping("/deletebyid/{carId}")
	    public String deleteByCarId(@PathVariable int carId) {
	        return service.deleteByCarId(carId);
	    }

	    @GetMapping("/getbybrand/{brand}")
	    public List<Car> getByBrand(@PathVariable String brand) {
	        return service.getByBrand(brand);
	    }

	  

	    @GetMapping("/getbyprice/{price}")
	    public List<Car> getByPriceLessThan(@PathVariable double price) {
	        return service.getByPriceLessThan(price);
	    }

	    @GetMapping("/getaffordable/orderbyprice/{price}")
	    public List<Car> getAffordableSorted(@PathVariable double price) {
	        return service.findAffordableCarsSortedByPrice(price);
	    }
	}


