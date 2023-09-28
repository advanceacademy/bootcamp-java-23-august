package com.advanceacademy.moonlighthotel.service.hotel.impl;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository repository) {
        this.roomRepository = repository;
    }

    //Creates a new room in the system
    @Override
    public Room createRoom(Room room) {
        try {
            return roomRepository.save(room);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateRecordException(String.format("Room with number %d already exists.", room.getRoomNumber()));
        }
    }

    //Retrieves a room from the database by its unique identifier
    @Override
    public Room getRoomById(Long id) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        if (foundRoom.isPresent()){
            return foundRoom.get();
        }else {
            throw new ResourceNotFoundException(String.format("Room with id %d does not exists", id));
        }
    }

    //Retrieves a list of all rooms from the database
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Update the attributes of the foundRoom with the new values and then save it
    @Override
    public Room updateRoom(Long id, Room room) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        if (foundRoom.isPresent()){
            Room updateRoom = foundRoom.get();
            updateRoom.setRoomNumber(room.getRoomNumber());
            updateRoom.setRoomType(room.getRoomType());
            updateRoom.setRoomView(room.getRoomView());
            updateRoom.setRoomPrice(room.getRoomPrice());
            updateRoom.setRoomFacilities(room.getRoomFacilities());
            updateRoom.setReservations(room.getReservations());
            return roomRepository.save(updateRoom);
        }else {
            throw new ResourceNotFoundException(String.format("Room with id %d not found", id));
        }
    }

    //Delete a room by its unique identifier
    @Override
    public void deleteRoomById(Long id) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        if (foundRoom.isPresent()){
            roomRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Room with %d not found",id));
        }
    }

    @Override
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return roomRepository.findByRoomType(roomType);
    }

    @Override
    public Room getRoomByRoomNumber(Integer roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

   @Override
    public List<Room> getRoomsByRoomView(RoomView roomView) {
        return roomRepository.findByRoomView((roomView));
    }

    @Override
    public List<Room> getRoomsByPrice(Double price) {
        return roomRepository.findByRoomPrice(price);
    }

    @Override
    public List<Room> getRoomsByMaxPeople(Integer maxPeople) {
        return roomRepository.findByMaxPeople(maxPeople);
    }



}
