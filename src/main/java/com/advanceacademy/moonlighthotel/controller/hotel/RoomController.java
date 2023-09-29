package com.advanceacademy.moonlighthotel.controller.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<List<Room>> getRoomsByRoomView(@PathVariable RoomView roomView) {
        List<Room> rooms = roomService.getRoomsByRoomView(roomView);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/by-room-price/{price}")
    public ResponseEntity<List<Room>> getRoomsByPrice(@PathVariable Double price) {
        List<Room> rooms = roomService.getRoomsByPrice(price);
        return ResponseEntity.ok(rooms);
    }


    @GetMapping("/by-max-people/{maxPeople}")
    public ResponseEntity<?> getRoomsByMaxPeople(@PathVariable Integer maxPeople) {
        if (maxPeople < 2 || maxPeople > 4) {
            return ResponseEntity.badRequest().body("No available rooms !");
        }

        List<Room> rooms = roomService.getRoomsByMaxPeople(maxPeople);

        return !rooms.isEmpty()
                ? ResponseEntity.ok(rooms)
                : ResponseEntity.noContent().header("X-Error-Message", "No available rooms for " + maxPeople + " people.").build();
       }

   }




