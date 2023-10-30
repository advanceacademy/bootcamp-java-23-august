package com.advanceacademy.moonlighthotel.dto.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.user.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TableReservationResponse {

    private Long id;
    private LocalDate date;
    private LocalTime hour;
    private RestaurantZone zone;
    private boolean isSmoking;
    private Double price;
    private TableRestaurant tableNumber;
    private Integer numberOfPeople;
    private User user;
}
