package com.advanceacademy.moonlighthotel.repository.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRestaurantRepository extends JpaRepository<SeatRestaurant, Long> {

}
