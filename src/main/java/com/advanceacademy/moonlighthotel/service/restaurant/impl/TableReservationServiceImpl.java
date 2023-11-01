package com.advanceacademy.moonlighthotel.service.restaurant.impl;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.repository.restaurant.TableReservationRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TableReservationServiceImpl implements TableReservationService {
    private final TableReservationRepository tableReservationRepository;

    @Autowired
    public TableReservationServiceImpl(TableReservationRepository tableReservationRepository) {
        this.tableReservationRepository = tableReservationRepository;
    }

    @Override
    public TableReservation createReservation(TableReservation reservation) {
        return tableReservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
      tableReservationRepository.deleteById(id);
    }

    @Override
    public List<TableReservation> getReservationsByUserId(Long userId) {
        return tableReservationRepository.findByUserId(userId);
    }

    @Override
    public List<TableReservation> getReservationsByTableId(Long tableId) {
        return tableReservationRepository.findByTableId(tableId);
    }

    @Override
    public List<TableReservation> getReservationsByDate(LocalDate date) {
        return tableReservationRepository.findByDate(date);
    }

    @Override
    public List<TableReservation> findReservationsByTableAndDate(TableRestaurant table, LocalDate date) {
        return tableReservationRepository.findByTableAndDate(table, date);
    }

    @Override
    public List<TableReservation> getReservationsByDateAndHour(LocalDate date, LocalTime time) {
        return tableReservationRepository.findByDateAndHour(date, time);
    }

    // Custom method to check if the requested hour is valid based on the existing reservation hour
    @Override
    public boolean isRequestedHourValid(LocalTime requestedHour, List<TableReservation> existingReservations) {
        for (TableReservation existingReservation : existingReservations) {
            LocalTime validHour = existingReservation.getHour().plusHours(1); // Add 1 hour to the existing hour
            if (requestedHour.isBefore(validHour)) {
                return false; // Requested hour conflicts with an existing reservation
            }
        }
        return true; // Requested hour is valid
    }
}



