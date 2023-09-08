package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableZone;

import java.util.List;

public interface TableRestaurantService {

        TableRestaurant getTableById(Long id);
        List<TableRestaurant> getTablesByZone(TableZone zone);
        List<TableRestaurant> getSmokingTables(Boolean isSmoking);
        List<TableRestaurant> getTablesBySeats(Integer seats);

}
