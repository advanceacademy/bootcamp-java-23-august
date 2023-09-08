package com.advanceacademy.moonlighthotel.service.restaurant.impl;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.repository.TableReservationRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.TableReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
}



