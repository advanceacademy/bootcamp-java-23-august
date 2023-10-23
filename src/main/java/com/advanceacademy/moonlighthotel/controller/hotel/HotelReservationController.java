package com.advanceacademy.moonlighthotel.controller.hotel;

import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationRequestDTO;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.service.hotel.RoomReservationService;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotel-reservations")
public class HotelReservationController {

    private final RoomReservationService roomReservationService;
    private final UserService userService;
    private final RoomService roomService;

    @Autowired
    public HotelReservationController(RoomReservationService roomReservationService, UserService userService, RoomService roomService) {
        this.roomReservationService = roomReservationService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> reserveRoom(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RoomReservationRequestDTO roomReservationRequestDto) {
        if (userDetails != null) {
            // Extract the user ID from userDetails
            Long userId = getUserIdFromUserDetails(userDetails);
            User user = userService.getUserById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));

            // Check if the requested room exists
            Long roomId = roomReservationRequestDto.getRoomId();
            if (roomId != null) {
                Optional<Room> roomOptional = Optional.ofNullable(roomService.getRoomById(roomId));
                if (roomOptional.isPresent()) {
                    Room room = roomOptional.get();

                    // Create a new room reservation
                    RoomReservation roomReservation = RoomReservation.builder().startDate(roomReservationRequestDto.getStartDate()).endDate(roomReservationRequestDto.getEndDate()).adult(roomReservationRequestDto.getAdult()).children(roomReservationRequestDto.getChildren()).room(room).user(user).paymentStatus(PaymentStatus.PAID).build();

                    // Save the room reservation
                    try {
                        roomReservationService.createRoomReservation(roomReservation);
                        return ResponseEntity.ok("Room reservation successful.");
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room reservation failed: " + e.getMessage());
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room ID is null");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }


    }

    private Long getUserIdFromUserDetails(UserDetails userDetails) {
        if (userDetails != null) {
            String username = userDetails.getUsername();
            Optional<User> userOptional = userService.getUserByUsername(username);

            if (userOptional.isPresent()) {
                return userOptional.get().getId();
            } else {
                throw new NoSuchElementException("User not found by username: " + username);
            }
        } else {
            throw new IllegalArgumentException("UserDetails is null");
        }
    }
}
