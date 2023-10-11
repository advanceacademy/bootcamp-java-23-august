package com.advanceacademy.moonlighthotel.controller.car;

import com.advanceacademy.moonlighthotel.converter.car.CarConverter;
import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/cars")
public class CarController {

    private final CarService carService;
    private final CarConverter carConverter;

    @Autowired
    public CarController(CarService carService, CarConverter converter) {
        this.carService = carService;
        this.carConverter = converter;
    }

    @GetMapping
    public ResponseEntity<List<CarBaseResponseDto>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/by-category/{categoryId}")
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByCategory(@PathVariable Long categoryId){
        List<Car> cars = carService.getCarsByCategory(categoryId);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/by-year/{year}")
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByYear(@PathVariable Integer year){
        List<Car> cars = carService.getCarsByYear(year);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/by-model/{model}")
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByModel(@PathVariable String model){
        List<Car> cars = carService.getCarsByModel(model);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/by-make/{make}")
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByMake(@PathVariable String make){
        List<Car> cars = carService.getCarsByMake(make);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarBaseResponseDto> getCarById(@PathVariable Long carId){
        Optional<Car> car = carService.getCarById(carId);
        if (car.isPresent()){
            CarBaseResponseDto carBaseResponseDto = carConverter.responseDto(car.get());
            return ResponseEntity.ok(carBaseResponseDto);
        }else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/by-type/{carType}")
    public ResponseEntity<List<CarBaseResponseDto>> getCarsByType(@PathVariable String carType){
        List<Car> cars = carService.getCarsByType(carType);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }

    @GetMapping(value = "/by-seats/{seats}")
    public ResponseEntity<List<CarBaseResponseDto>> getCarsBySeats(@PathVariable Integer seats){
        List<Car> cars = carService.getCarsBySeats(seats);
        List<CarBaseResponseDto> carBaseResponseDtos = cars.stream()
                .map(carConverter::responseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carBaseResponseDtos);
    }
}
