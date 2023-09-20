package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;

import java.util.List;

public interface RoomService {

    //Create a new room
    Room createRoom(Room room);

    //Get room by ID
    Room getRoomById(Long id);

    //Retrieve all rooms
    List<Room> getAllRooms();

    //Update an existing room
    Room updateRoom(Long id, Room room);

    //Delete room by ID
    void deleteRoomById(Long id);

    List<Room> getRoomsByRoomType(RoomType roomType);

    Room getRoomByRoomNumber(Integer roomNumber);

    List<Room> getRoomsByRoomView(String roomView);

    List<Room> getRoomsByPrice(Double price);

}
