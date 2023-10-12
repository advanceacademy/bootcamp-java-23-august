package com.advanceacademy.moonlighthotel.service.car.impl;


import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;


    @Override
    public Car addCar(Car car) {
        Optional<Car> savedCar = carRepository.findById(car.getId());
        if (savedCar.isPresent()) {
            throw new EntityNotFoundException("Car with Id# " + car.getId() + " has already existed");
        }
        return carRepository.save(car);

    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarsByCategory(java.lang.Long categoryId) {

        return carRepository.findByCarCategoryId(categoryId).orElseThrow();
    }

    @Override
    public List<Car> getCarsByYear(Integer carYear) {
        return carRepository.findByYear(carYear).orElseThrow();
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        return carRepository.findByModel(model).orElseThrow();
    }

    @Override
    public List<Car> getCarsByMake(String make) {
        return carRepository.findByMake(make).orElseThrow();
    }

    @Override
    public List<Car> getCarsByType(String carType) {
        return carRepository.findByCarType(CarType.valueOf(carType.toUpperCase()));
    }

    @Override
    public List<Car> getCarsBySeats(Integer seats) {
        return carRepository.findBySeats(seats);
    }

    @Override
    public Optional<Car> getCarById(java.lang.Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car updateCar(Car car, java.lang.Long id) {
        Optional<Car> foundCar = carRepository.findById(id);
        if (foundCar.isPresent()) {
            Car updatedCar = Car.builder()
                    .make(car.getMake())
                    .model(car.getModel())
                    .year(car.getYear())
                    .plateNumber(car.getPlateNumber())
                    .carCategory(car.getCarCategory())
                    .fileResources(car.getFileResources())
                    .build();
            return carRepository.save(updatedCar);
        } else
            throw new ResourceNotFoundException(String.format("Car with id %d not found", id));

    }

    @Override
    public void deleteById(java.lang.Long id) {
        carRepository.deleteById(id);
    }
}
