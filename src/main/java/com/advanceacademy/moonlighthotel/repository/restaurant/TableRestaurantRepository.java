package com.advanceacademy.moonlighthotel.repository.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant,Long> {

    Optional<TableRestaurant> findById(Long id);

    Optional<TableRestaurant> findByNumber(Integer number);

    List<TableRestaurant> findByZone(RestaurantZone zone);

    List<TableRestaurant> findByIsSmoking(Boolean isSmoking);

    List<TableRestaurant> findBySeats(Integer seats);
}
