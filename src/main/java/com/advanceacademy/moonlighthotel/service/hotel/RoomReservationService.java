package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;

import java.util.List;

public interface RoomReservationService {

    // Create a new room reservation
    RoomReservation createRoomReservation(RoomReservation roomReservation);

    // Retrieve a room reservation by ID
    RoomReservation getRoomReservationById(Long id);

    // Retrieve all room reservations
    List<RoomReservation> getAllRoomReservations();

    // Update an existing room reservation
    RoomReservation updateRoomReservation(Long id, RoomReservation roomReservation);

    // Delete a room reservation by ID
    void deleteRoomReservationById(Long id);
}
