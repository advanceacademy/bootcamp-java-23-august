package com.advanceacademy.moonlighthotel.service.restaurant.impl;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import com.advanceacademy.moonlighthotel.repository.restaurant.SeatRestaurantRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.SeatRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SeatRestaurantServiceImpl implements SeatRestaurantService {

    private final SeatRestaurantRepository seatRestaurantRepository;

    @Autowired
    public SeatRestaurantServiceImpl(SeatRestaurantRepository seatRestaurantRepository) {
        this.seatRestaurantRepository = seatRestaurantRepository;
    }

    @Override
    public SeatRestaurant createSeat(SeatRestaurant seat) {
        return seatRestaurantRepository.save(seat);
    }

    @Override
    public SeatRestaurant getSeatById(Long id) {
        return seatRestaurantRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("There is no restaurant bar seat matching id %s.", id)));
    }

    @Override
    public SeatRestaurant getSeatByNumber(Integer number){
        return seatRestaurantRepository.findByNumber(number).orElseThrow(() -> new NoSuchElementException(String.format("There is no restaurant bar seat matching number %s.", number)));
    }

}
