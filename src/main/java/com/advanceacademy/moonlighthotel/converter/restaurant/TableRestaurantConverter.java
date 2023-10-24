package com.advanceacademy.moonlighthotel.converter.restaurant;

import com.advanceacademy.moonlighthotel.dto.restaurant.TableRestaurantResponse;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import org.springframework.stereotype.Component;

@Component
public class TableRestaurantConverter {

    public TableRestaurantResponse toTableRestaurantResponse(TableRestaurant tableRestaurant){

        return TableRestaurantResponse.builder()
                .id(tableRestaurant.getId())
                .tableNumber(tableRestaurant.getNumber())
                .zone(tableRestaurant.getZone())
                .isSmoking(tableRestaurant.getIsSmoking())
                .seats(tableRestaurant.getSeats())
                .build();
    }
}
