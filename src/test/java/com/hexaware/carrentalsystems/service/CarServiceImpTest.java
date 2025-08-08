
package com.hexaware.carrentalsystems.service;

import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.entities.Car.Availability;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // Optional, for controlling test execution order
public class CarServiceImpTest {

    @Autowired
    private CarServiceImp carService;

    @Test
    @Order(1)
    public void testAddCar() {
        CarDto dto = new CarDto();
        dto.setCarId(10);
        dto.setBrand("Kia");
        dto.setModel("Seltos");
        dto.setPricePerDay(2000.0);
        dto.setAvailability(Availability.AVAILABLE);

        Car savedCar = carService.addCar(dto);
        assertNotNull(savedCar);
        assertEquals("Kia", savedCar.getBrand());
    }

    @Test
    @Order(2)
    public void testGetCarById_Success() {
        Car car = carService.getByCarId(10);
        assertNotNull(car);
        assertEquals("Kia", car.getBrand());
    }

   


    @Test
    @Order(3)
    public void testGetAllCars() {
        List<Car> cars = carService.getAllCars();
        assertTrue(cars.size() > 0);
    }

    @Test
    @Order(4)
    public void testGetByBrand() {
        List<Car> cars = carService.getByBrand("Hyundai");
        assertTrue(cars.stream().allMatch(c -> c.getBrand().equals("Hyundai")));
    }

   
}
