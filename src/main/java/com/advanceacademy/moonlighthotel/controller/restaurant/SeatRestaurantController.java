package com.advanceacademy.moonlighthotel.controller.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import com.advanceacademy.moonlighthotel.service.restaurant.SeatRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant-bar-seat")
public class SeatRestaurantController{
    @Autowired
    private SeatRestaurantService seatRestaurantService;

    @GetMapping(path = "/get-by-id/{id}")
    public ResponseEntity<SeatRestaurant> getRestaurantBarSeatById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(seatRestaurantService.getSeatById(id));
    }

    @GetMapping(path = "/get-by-number/{number}")
    public ResponseEntity<SeatRestaurant> getRestaurantBarSeatByNumber(@PathVariable Integer number){
        return ResponseEntity.status(HttpStatus.FOUND).body(seatRestaurantService.getSeatByNumber(number));
    }
}
