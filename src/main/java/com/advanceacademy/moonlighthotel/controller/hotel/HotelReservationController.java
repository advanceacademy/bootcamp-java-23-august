package com.advanceacademy.moonlighthotel.controller.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomReservationRepository;
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

    private final RoomReservationRepository roomReservationRepository;
    private final UserService userService;

    @Autowired
    public HotelReservationController(RoomReservationRepository roomReservationRepository, UserService userService) {
        this.roomReservationRepository = roomReservationRepository;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> reserveRoom(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody RoomReservation roomReservation
    ) {
        if (userDetails != null) {
            // Extract the user ID from userDetails
            Long userId = getUserIdFromUserDetails(userDetails);
            User user = userService.getUserById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            // Validate the room reservation and save it
            try {
                roomReservationRepository.save(roomReservation);
                return ResponseEntity.ok("Room reservation successful.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room reservation failed: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated.");
        }
    }

    private Long getUserIdFromUserDetails(UserDetails userDetails) {

        String username = userDetails.getUsername();


        Optional<User> user = userService.getUserByUsername(username);


        return user.map(User::getId).orElse(null);
    }


}


