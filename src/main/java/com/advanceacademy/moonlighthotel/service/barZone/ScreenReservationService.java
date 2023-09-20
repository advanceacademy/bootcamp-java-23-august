package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenReservation;

import java.util.List;

public interface ScreenReservationService {

    //Create a new screen reservation
    ScreenReservation createScreenReservation(ScreenReservation ScreenReservation);

    //Get screen reservation by ID
    ScreenReservation getScreenReservationById(Long id);

    //Retrieve all screen reservations
    List<ScreenReservation> getAllScreenReservations();

    //Update an existing screen reservation
    ScreenReservation updateScreenReservation(Long id, ScreenReservation ScreenReservation);

    //Delete screen reservation by ID
    void deleteScreenReservationById(Long id);
}
