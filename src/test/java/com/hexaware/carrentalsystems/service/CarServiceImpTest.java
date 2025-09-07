
package com.hexaware.carrentalsystems.service;



import com.hexaware.carrentalsystems.dto.CarDto;
import com.hexaware.carrentalsystems.entities.Car;
import com.hexaware.carrentalsystems.exceptions.CarNotFoundException;
import com.hexaware.carrentalsystems.repository.ICarRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarServiceImpTest {

    @Autowired
    private CarServiceImp carService;

    @Autowired
    private ICarRepository carRepo;

    @Test
    void testAddCar() {
        CarDto dto = new CarDto();
        dto.setCarId(101);
        dto.setBrand("Toyota");
        dto.setModel("Corolla");
        dto.setPricePerDay(3000);
        dto.setAvailability("AVAILABLE");

        Car savedCar = carService.addCar(dto);

        assertNotNull(savedCar);
        assertEquals("Toyota", savedCar.getBrand());
    }

    @Test
    void testGetByCarId() {
        CarDto dto = new CarDto();
        dto.setCarId(102);
        dto.setBrand("Honda");
        dto.setModel("Civic");
        dto.setPricePerDay(3500);
        dto.setAvailability("AVAILABLE");

        carService.addCar(dto);

        Car fetchedCar = carService.getByCarId(102);
        assertNotNull(fetchedCar);
        assertEquals("Civic", fetchedCar.getModel());
    }

    @Test
    void testUpdateCar() {
        CarDto dto = new CarDto();
        dto.setCarId(103);
        dto.setBrand("Ford");
        dto.setModel("Fiesta");
        dto.setPricePerDay(2500);
        dto.setAvailability("AVAILABLE");

        carService.addCar(dto);

        Car carToUpdate = carService.getByCarId(103);
        carToUpdate.setPricePerDay(2700);

        Car updatedCar = carService.updateCar(carToUpdate);
        assertEquals(2700, updatedCar.getPricePerDay());
    }

    @Test
    void testDeleteCar() {
        CarDto dto = new CarDto();
        dto.setCarId(104);
        dto.setBrand("BMW");
        dto.setModel("X1");
        dto.setPricePerDay(5000);
        dto.setAvailability("AVAILABLE");

        carService.addCar(dto);

        String message = carService.deleteByCarId(104);
        assertEquals("Car deleted successfully", message);

        assertThrows(CarNotFoundException.class, () -> carService.getByCarId(104));
    }
}

