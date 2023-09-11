package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomFacility;

import java.util.List;

public interface RoomFacilityService {

    // Create a new room facility
    RoomFacility createRoomFacility(RoomFacility roomFacility);

    // Retrieve a room facility by ID
    RoomFacility getRoomFacilityById(Long id);

    // Retrieve all room facilities
    List<RoomFacility> getAllRoomFacilities();

    // Update an existing room facility
    RoomFacility updateRoomFacility(Long id, RoomFacility roomFacility);

    // Delete a room facility by ID
    void deleteRoomFacilityById(Long id);
}
