package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatReservation;

import java.time.LocalDate;
import java.util.List;

public interface SeatReservationService {

    SeatReservation createReservation(SeatReservation reservation);
    void deleteReservation(Long id);
    List<SeatReservation > getReservationsByUserId(Long userId);
    List<SeatReservation > getReservationsByDate(LocalDate date);

}
