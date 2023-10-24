package com.advanceacademy.moonlighthotel.service.car.impl;


import com.advanceacademy.moonlighthotel.converter.car.CarConverter;
import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CarConverter carConverter;

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
    public List<Car> getCarsByCategory(Long categoryId) {

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
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<CarBaseResponseDto> getAvailableCarsByDateAndSeats(LocalDate date, Integer seats){
        List<Car> foundCars = carRepository.findByDateAndSeats(date, seats);
        if(foundCars.isEmpty()){
            throw new NoSuchElementException("There are no cars matching the criteria provided.");
        }
        return foundCars.stream().map(carConverter::responseDto).collect(Collectors.toList());
    }

    public List<CarBaseResponseDto> getAvailableCarsByDateSeatsCategoryModel(LocalDate date, Integer seats, Long categoryId, String model){
        List<Car> foundCars = carRepository.findByDateSeatsCategoryModel(date, seats, categoryId, model);
        if(foundCars.isEmpty()){
            throw new NoSuchElementException("There are no available cars matching the criteria provided.");
        }
        return foundCars.stream().map(carConverter::responseDto).collect(Collectors.toList());
    }

    @Override
    public Car updateCar(Car car, Long id) {
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
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
