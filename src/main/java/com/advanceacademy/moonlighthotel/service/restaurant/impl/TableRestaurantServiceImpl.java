package com.advanceacademy.moonlighthotel.service.restaurant.impl;


import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableZone;
import com.advanceacademy.moonlighthotel.repository.TableRestaurantRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.TableRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TableRestaurantServiceImpl implements TableRestaurantService {
    private final TableRestaurantRepository tableRestaurantRepository;

    @Autowired
    public TableRestaurantServiceImpl(TableRestaurantRepository tableRestaurantRepository) {
        this.tableRestaurantRepository = tableRestaurantRepository;
    }

    @Override
    public TableRestaurant getTableById(Long id) {
        return tableRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<TableRestaurant> getTablesByZone(TableZone zone) {
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