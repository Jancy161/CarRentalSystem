package com.hexaware.carrentalsystems.service;
import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.exceptions.CarNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.carrentalsystems.repository.ICarRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CarServiceImp implements ICarService {
	 @Autowired
	    ICarRepository CarRepo;
	 
	 @Override
	 //adding new car
		public Car addCar(CarDto dto) {
			
			Car car = new Car();
			
				car.setCarId(dto.getCarId());
				car.setBrand(dto.getBrand());
				car.setModel(dto.getModel());
				car.setPricePerDay(dto.getPricePerDay());
				car.setAvailability(dto.getAvailability());
				
				log.info("Adding new car: {}", dto);
	
		
			return CarRepo.save(car);
		}

	   

	    @Override
	    //update car details
	    public Car updateCar(Car car) {
	        if (!CarRepo.existsById(car.getCarId())) {
	            throw new CarNotFoundException("Car not found with ID: " + car.getCarId());
	        }
	        return CarRepo.save(car);
	    }


	  
	    //select car by car id
	    public Car getByCarId(int carId) throws CarNotFoundException {
	        return CarRepo.findById(carId)
	                .orElseThrow(() -> new CarNotFoundException("Car not found with ID: " + carId));
	    }

	    
	    public String deleteByCarId(int carId) throws CarNotFoundException {
	    	  log.info("Fetching car by ID: {}", carId);
	        if (!CarRepo.existsById(carId)) {
	            throw new CarNotFoundException("Cannot delete. Car not found with ID: " + carId);
	        }
	        CarRepo.deleteById(carId);
	        log.debug("Car deleted successfully with ID: {}", carId);
	        return "Car deleted successfully";
	    }


	  
	    public List<Car> getAllCars() {
	    	log.info("Fetching all cars");
	        return CarRepo.findAll();
	    }

	    
	    public List<Car> getByBrand(String brand) {
	    	 log.info("Fetching cars by brand: {}", brand);
	        return CarRepo.findByBrand(brand);
	    }

	   
	    
	    public List<Car> getByPriceLessThan(double price) {
	        log.info("Fetching cars with price less than {}", price);

	        return CarRepo.findByPricePerDayLessThan(price);
	    }

	    public List<Car> findAffordableCarsSortedByPrice(double price) {
	        return CarRepo.findAffordableCarsSortedByPrice(price);
	    }



		

		
	}




