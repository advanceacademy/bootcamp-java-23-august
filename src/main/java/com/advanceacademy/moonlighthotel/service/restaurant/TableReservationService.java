package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import com.advanceacademy.moonlighthotel.entity.user.User;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TableReservationService {

    TableReservation createReservation(TableReservation reservation);

    void deleteReservation(Long id);

    List<TableReservation> getReservationsByUserId(Long userId);

    List<TableReservation> getReservationsByTableId(Long tableId);

    List<TableReservation> getReservationsByDate(LocalDate date);

    List<TableReservation> findReservationsByTableAndDate(TableRestaurant table, LocalDate date);

    List<TableReservation> getReservationsByDateAndHour(LocalDate date, LocalTime time);

    boolean isRequestedHourValid(LocalTime requestedHour, List<TableReservation> existingReservations);

    List<TableReservation> getAllTableReservations();

    List<TableReservation> getTableReservationByUser(User user);

}
