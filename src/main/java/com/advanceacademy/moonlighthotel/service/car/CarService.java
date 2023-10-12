package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    //Create
    Car addCar(Car car);

    //Retrieve
    List<Car> getAllCars();

    List<Car> getCarsByCategory(java.lang.Long categoryId);

    List<Car> getCarsByYear(Integer carYear);

    List<Car> getCarsByModel(String model);

    List<Car> getCarsByMake(String make);

    List<Car> getCarsByType(String carType);

    List<Car> getCarsBySeats(Integer seats);

    Optional<Car> getCarById(java.lang.Long id);

    //Update
    Car updateCar(Car updatedCar, java.lang.Long id);

    //Delete
    void deleteById(java.lang.Long id);

}
