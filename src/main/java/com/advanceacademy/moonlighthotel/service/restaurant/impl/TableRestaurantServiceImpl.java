package com.advanceacademy.moonlighthotel.service.restaurant.impl;


import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.repository.restaurant.TableRestaurantRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {
    private final TableRestaurantRepository tableRestaurantRepository;

    @Autowired
    public TableRestaurantServiceImpl(TableRestaurantRepository tableRestaurantRepository) {
        this.tableRestaurantRepository = tableRestaurantRepository;
    }

    @Override
    public TableRestaurant createTable(TableRestaurant table){
        return tableRestaurantRepository.save(table);
    }

    @Override
    public TableRestaurant getTableById(Long id) {
        return tableRestaurantRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("There is no restaurant table matching id %s.", id)));
    }

    @Override
    public TableRestaurant getTableByNumber(Integer number){
        return tableRestaurantRepository.findByNumber(number).orElseThrow(() -> new NoSuchElementException(String.format("There is no restaurant table matching number %s.", number)));
    }

    @Override
    public List<TableRestaurant> getTablesByZone(RestaurantZone zone) {
        return tableRestaurantRepository.findByZone(zone);
    }

    @Override
    public List<TableRestaurant> getSmokingTables(Boolean isSmoking) {
        return tableRestaurantRepository.findByIsSmoking(isSmoking);
    }

    @Override
    public List<TableRestaurant> getTablesBySeats(Integer seats) {
        return tableRestaurantRepository.findBySeats(seats);
    }
}