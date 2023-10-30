package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarService {
    //Create
    Car addCar(Car car);

    //Retrieve
    List<Car> getAllCars();

    List<Car> getCarsByCategory(Long categoryId);

    List<Car> getCarsByYear(Integer carYear);

    List<Car> getCarsByModel(String model);

    List<Car> getCarsByMake(String make);

    List<Car> getCarsByType(String carType);

    List<Car> getCarsBySeats(Integer seats);

    Optional<Car> getCarById(Long id);

    List<CarBaseResponseDto> getAvailableCarsByDateAndSeats(LocalDate date, Integer seats);

    List<CarBaseResponseDto> getAvailableCarsByDateSeatsCategoryModel(LocalDate date, Integer seats, Long categoryId, String model);

    //Update
    Car updateCar(Car updatedCar, Long id);

    //Delete
    void deleteById(Long id);

}
