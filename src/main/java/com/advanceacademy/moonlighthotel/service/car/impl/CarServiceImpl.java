package com.advanceacademy.moonlighthotel.service.car.impl;


import com.advanceacademy.moonlighthotel.entity.car.Car;
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
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car updateCar(Car updatedCar) {
        return carRepository.save(updatedCar);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
