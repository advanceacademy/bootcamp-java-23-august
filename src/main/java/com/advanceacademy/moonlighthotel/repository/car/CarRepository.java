package com.advanceacademy.moonlighthotel.repository.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<List<Car>> findByCarCategoryId(Long carCategoryId);

    Optional<List<Car>> findByYear(Integer carYear);

    Optional<List<Car>> findByModel(String model);

    Optional<List<Car>> findByMake(String make);

    @Query("SELECT c FROM Car c INNER JOIN c.carCategory cc WHERE cc.carTypes = :carType")
    List<Car> findByCarType(@Param("carType") CarType carType);

    @Query("SELECT c FROM Car c INNER JOIN c.carCategory cc WHERE cc.seats = :seats")
    List<Car> findBySeats(@Param("seats") Integer seats);

}
