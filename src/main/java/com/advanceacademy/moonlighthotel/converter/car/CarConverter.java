package com.advanceacademy.moonlighthotel.converter.car;

import com.advanceacademy.moonlighthotel.dto.car.CarBaseRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import com.advanceacademy.moonlighthotel.service.car.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    private final CarCategoryService carCategoryService;

    @Autowired
    public CarConverter(CarCategoryService carCategoryService) {
        this.carCategoryService = carCategoryService;
    }

    public Car toCar(CarBaseRequestDto requestDto){

        CarCategory carCategory = carCategoryService.getById(requestDto.getCarCategoryId()).orElseThrow();

        return Car.builder()
                .make(requestDto.getMake())
                .model(requestDto.getModel())
                .year(requestDto.getYear())
                .plateNumber(requestDto.getPlateNumber())
                .carCategory(carCategory)
                .build();
    }

    public CarBaseResponseDto responseDto(Car car){
        CarCategory carCategory = car.getCarCategory();

        return CarBaseResponseDto.builder()
                .id(car.getId())
                .make(car.getMake())
                .model(car.getModel())
                .year(car.getYear())
                .plateNumber(car.getPlateNumber())
                .carCategoryId(carCategory.getId())
                .carType(carCategory.getCarTypes().name())
                .seats(carCategory.getSeats())
                .pricePerDay(carCategory.getPricePerDay())
                .build();
    }
}
