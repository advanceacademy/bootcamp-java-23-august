package com.advanceacademy.moonlighthotel.repository.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<List<Car>> findByCarCategoryId(Long carCategoryId);

}
