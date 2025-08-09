package com.hexaware.carrentalsystems.service;
import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.exceptions.CarNotFoundException;

import java.util.*;


public interface ICarService {

	//public Car addCar(Car car);
    public Car updateCar(Car car) ;
    public Car getByCarId(int carId) throws CarNotFoundException;
    public String deleteByCarId(int carId) throws CarNotFoundException;
    public List<Car> getAllCars();

    public List<Car> getByBrand(String brand);
    public List<Car> getByPriceLessThan(double price);
    public List<Car> findAffordableCarsSortedByPrice(double price);
	public Car addCar(CarDto dto);

}

