package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;

import java.time.LocalDate;
import java.util.List;

public interface TableRestaurantService {

    TableRestaurant createTable(TableRestaurant table);

    TableRestaurant getTableById(Long id);

    TableRestaurant getTableByNumber(Integer number);

    List<TableRestaurant> getTablesByZone(RestaurantZone zone);

    List<TableRestaurant> getSmokingTables(Boolean isSmoking);

    List<TableRestaurant> getTablesBySeats(Integer seats);

        List<TableRestaurant> getAllTables();

        Boolean isNumberOfPeopleOk(Integer numberOfPeople, Integer tableSeats);

}
