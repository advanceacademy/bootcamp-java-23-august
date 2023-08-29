package com.advanceacademy.moonlighthotel.repository;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableZone;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant,Long> {

    Optional<TableRestaurant> findById(Long id);

    List<TableRestaurant> findByZone(TableZone zone);


    List<TableRestaurant> findByIsSmoking(Boolean isSmoking);

    List<TableRestaurant> findBySeats(Integer seats);
}
