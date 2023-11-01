package com.advanceacademy.moonlighthotel.dto.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TableRestaurantResponse {

    private Long id;
    private Integer tableNumber;
    private RestaurantZone zone;
    private Boolean isSmoking;
    private Integer seats;
}
