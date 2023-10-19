package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;

import java.time.LocalDate;
import java.util.List;

public interface TableReservationService {

    TableReservation createReservation(TableReservation reservation);

    void deleteReservation(Long id);

    List<TableReservation> getReservationsByUserId(Long userId);

    List<TableReservation> getReservationsByTableId(Long tableId);

    List<TableReservation> getReservationsByDate(LocalDate date);

}
