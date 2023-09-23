package com.advanceacademy.moonlighthotel.controller.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;


    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-room-type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByRoomType(@PathVariable RoomType roomType) {
        List<Room> rooms = roomService.getRoomsByRoomType(roomType);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-room-number/{roomNumber}")
    public ResponseEntity<Room> getRoomByRoomNumber(@PathVariable Integer roomNumber) {
        Room room = roomService.getRoomByRoomNumber(roomNumber);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/by-room-view/{roomView}")
    public ResponseEntity<List<Room>> getRoomsByRoomView(@PathVariable String roomView) {
        List<Room> rooms = roomService.getRoomsByRoomView(roomView);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-room-price/{price}")
    public ResponseEntity<List<Room>> getRoomsByPrice(@PathVariable Double price) {
        List<Room> rooms = roomService.getRoomsByPrice(price);
        return ResponseEntity.ok(rooms);
    }


    @GetMapping("/max-people")
    public ResponseEntity<List<RoomType>> getRoomTypesByMaxPeople(@RequestParam int maxPeople) {
        List<RoomType> matchingRoomTypes = Arrays.stream(RoomType.values())
                 // I apply streams to all values of the RoomType enum.

                .filter(roomType -> roomType.getMaxPeople() <= maxPeople)
                 // I filter the room types where the maximum number of people is less than or equal to maxPeople.

                .collect(Collectors.toList());
                 // I collect the filtered values into a list and return them.

        return ResponseEntity.ok(matchingRoomTypes);
        // I return the list of filtered room types as an HTTP response with a status of 200 (OK).
    }
}

