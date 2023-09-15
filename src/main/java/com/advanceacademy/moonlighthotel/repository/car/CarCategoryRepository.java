package com.advanceacademy.moonlighthotel.repository.car;

import com.advanceacademy.moonlighthotel.entity.car.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {

    Optional<CarCategory> findBySeats(Integer seats);

}
