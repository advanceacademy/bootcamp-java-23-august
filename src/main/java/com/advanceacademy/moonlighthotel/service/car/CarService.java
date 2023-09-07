package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.dto.car.CarBaseRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
//Create
    Car addCar(Car car);
    //Retrieve
    List<Car> getAllCars();

  Optional<Car> getCarById(Long id);
  //Update
    Car updateCar(Car updatedCar);

   //Delete
    void deleteById(Long id);

}
