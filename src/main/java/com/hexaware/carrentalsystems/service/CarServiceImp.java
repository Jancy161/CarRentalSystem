package com.hexaware.carrentalsystems.service;
import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.exceptions.CarNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.repository.ICarRepository;



@Service
public  class CarServiceImp implements ICarService {
	 @Autowired
	    ICarRepository CarRepo;
	 
	 @Override
		public Car addCar(CarDto dto) {
			
			Car car = new Car();
			
				car.setCarId(dto.getCarId());
				car.setBrand(dto.getBrand());
				car.setModel(dto.getModel());
				car.setPricePerDay(dto.getPricePerDay());
				car.setAvailability(dto.getAvailability());
				
				
		
			return CarRepo.save(car);
		}

	    public Car addCar(Car car) {
	        return CarRepo.save(car); // insert
	    }

	    @Override
	    public Car updateCar(Car car) {
	        if (!CarRepo.existsById(car.getCarId())) {
	            throw new CarNotFoundException("Car not found with ID: " + car.getCarId());
	        }
	        return CarRepo.save(car);
	    }


	  
	    
	    public Car getByCarId(int carId) throws CarNotFoundException {
	        return CarRepo.findById(carId)
	                .orElseThrow(() -> new CarNotFoundException("Car not found with ID: " + carId));
	    }

	    
	    public String deleteByCarId(int carId) throws CarNotFoundException {
	        if (!CarRepo.existsById(carId)) {
	            throw new CarNotFoundException("Cannot delete. Car not found with ID: " + carId);
	        }
	        CarRepo.deleteById(carId);
	        return "Car deleted successfully";
	    }


	  
	    public List<Car> getAllCars() {
	        return CarRepo.findAll();
	    }

	    
	    public List<Car> getByBrand(String brand) {
	        return CarRepo.findByBrand(brand);
	    }

	   
	    public List<Car> getByAvailability(Car.Availability availability) {
	        return CarRepo.findByAvailability(availability);
	    }

	    
	    public List<Car> getByPriceLessThan(double price) {
	        return CarRepo.findByPricePerDayLessThan(price);
	    }

	    public List<Car> findAffordableCarsSortedByPrice(double price) {
	        return CarRepo.findAffordableCarsSortedByPrice(price);
	    }

		
	}




