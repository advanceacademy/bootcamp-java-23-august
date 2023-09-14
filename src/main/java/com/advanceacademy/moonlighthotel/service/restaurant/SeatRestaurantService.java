package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;

public interface SeatRestaurantService {

    SeatRestaurant createSeat(SeatRestaurant seat);

    SeatRestaurant getSeatById(Long id);

}
