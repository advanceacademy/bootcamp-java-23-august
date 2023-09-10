package com.advanceacademy.moonlighthotel.service.hotel.impl;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomFacility;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomFacilityRepository;
import com.advanceacademy.moonlighthotel.service.hotel.RoomFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomFacilityServiceImpl implements RoomFacilityService {

    private final RoomFacilityRepository roomFacilityRepository;

    @Autowired
    public RoomFacilityServiceImpl(RoomFacilityRepository roomFacilityRepository) {
        this.roomFacilityRepository = roomFacilityRepository;
    }

    //Creates a new room facility in the system
    @Override
    public RoomFacility createRoomFacility(RoomFacility roomFacility) {
            return roomFacilityRepository.save(roomFacility);
    }

    //Retrieves a room facility from the database by its unique identifier
    @Override
    public RoomFacility getRoomFacilityById(Long id) {
        Optional<RoomFacility> foundRoomFacility = roomFacilityRepository.findById(id);
        if (foundRoomFacility.isPresent()){
            return foundRoomFacility.get();
        } else {
            throw new ResourceNotFoundException(String.format("Room facility with id %d not found", id));
        }
    }

    //Retrieves a list of all rooms facilities from the database
    @Override
    public List<RoomFacility> getAllRoomFacilities() {
        return roomFacilityRepository.findAll();
    }

    // Update the attributes of the foundRoomFacility with the new values and then save it
    @Override
    public RoomFacility updateRoomFacility(Long id, RoomFacility roomFacility) {
        Optional<RoomFacility> foundRoomFacility = roomFacilityRepository.findById(id);
        if (foundRoomFacility.isPresent()){
            RoomFacility updatedRoomFacility = foundRoomFacility.get();
            updatedRoomFacility.setFacilityName(roomFacility.getFacilityName());
            return roomFacilityRepository.save(updatedRoomFacility);
        } else {
            throw new ResourceNotFoundException(String.format("Room facility with id %d not found", id));
        }
    }

    //Delete a room facility by its unique identifier
    @Override
    public void deleteRoomFacilityById(Long id) {
        Optional<RoomFacility> foundRoomFacility = roomFacilityRepository.findById(id);
        if (foundRoomFacility.isPresent()){
            roomFacilityRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException(String.format("Room facility with id %d not found", id));
        }
    }
}
