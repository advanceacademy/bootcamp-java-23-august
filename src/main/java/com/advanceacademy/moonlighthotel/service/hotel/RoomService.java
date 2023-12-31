package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    //Create a new room
    Room createRoom(Room room);

    //Get room by ID
    Room getRoomById(Long id);

    //Retrieve all rooms
    List<Room> getAllRooms();

    List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate);

    List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, Integer adults);

    List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, Integer adults, Integer children);

    //Update an existing room
    Room updateRoom(Long id, Room room);

    //Delete room by ID
    void deleteRoomById(Long id);

    List<Room> getRoomsByRoomType(RoomType roomType);

    Room getRoomByRoomNumber(Integer roomNumber);

    List<Room> getRoomsByRoomView(RoomView roomView);

    List<Room> getRoomsByPrice(Double price);

    List<Room> getRoomsByMaxPeople(Integer maxPeople);

}
