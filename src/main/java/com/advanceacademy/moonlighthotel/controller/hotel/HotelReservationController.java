package com.advanceacademy.moonlighthotel.controller.hotel;

import com.advanceacademy.moonlighthotel.converter.hotel.RoomReservationConverter;
import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationRequestDTO;
import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationResponseDTO;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.service.hotel.RoomReservationService;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1")
public class HotelReservationController {

    private final RoomReservationService roomReservationService;
    private final UserService userService;
    private final RoomService roomService;

    private final RoomReservationConverter roomReservationConverter;

    @Autowired
    public HotelReservationController(RoomReservationService roomReservationService, UserService userService, RoomService roomService, RoomReservationConverter roomReservationConverter) {
        this.roomReservationService = roomReservationService;
        this.userService = userService;
        this.roomService = roomService;
        this.roomReservationConverter = roomReservationConverter;
    }

    @PostMapping("/user/room-reservation/create")
    public ResponseEntity<RoomReservationResponseDTO> reserveRoom(@Valid @RequestBody RoomReservationRequestDTO requestDTO) {

        Room requestedRoom = roomService.getRoomByRoomNumber(requestDTO.getRoomNumber());
        RoomReservation roomReservation = RoomReservation.builder()
                .startDate(requestDTO.getStartDate())
                .endDate(requestDTO.getEndDate())
                .adult(requestDTO.getAdult())
                .children(requestDTO.getChildren())
                .totalPrice(requestedRoom.getRoomPrice())
                .user(userService.findByEmail(userService.getAuthUserEmail()))
                .room(roomService.getRoomByRoomNumber(requestDTO.getRoomNumber()))
                .paymentStatus(PaymentStatus.PAID)
                .build();

        RoomReservation savedReservation = roomReservationService.createRoomReservation(roomReservation);


        return ResponseEntity.ok(roomReservationConverter.toResponseDto(savedReservation));
    }

    @GetMapping("/user/available")
    public ResponseEntity<?> getAvailableRoomsByDateRange(
            @RequestParam("start_date") LocalDate startDate,
            @RequestParam("end_date") LocalDate endDate,
            @RequestParam(value = "adults", required = false) Integer adults,
            @RequestParam(value = "children",required = false) Integer children) {
        List<Room> availableRooms = roomService.findAvailableRooms(startDate, endDate);


        if (availableRooms.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else if (adults != null && children != null){
            int requestedPeople = adults + children;
            if (requestedPeople > 4){
                return ResponseEntity.badRequest().body("there are no rooms for " + requestedPeople);

            }
            return ResponseEntity.ok(roomService.findAvailableRooms(startDate, endDate, adults, children));

        } else if (adults != null) {
            return ResponseEntity.ok(roomService.findAvailableRooms(startDate, endDate, adults));

        } else {
            return ResponseEntity.ok(roomService.findAvailableRooms(startDate, endDate));

        }

    }



    @GetMapping("/admin/room-reservation/get-all")
    public ResponseEntity<List<RoomReservationResponseDTO>> getAllRoomReservations(){
        List<RoomReservation> allRoomReservations = roomReservationService.getAllRoomReservations();

            if (allRoomReservations.isEmpty()){
                return ResponseEntity.notFound().build();
            }

        List<RoomReservationResponseDTO> reservationResponseDTOS = allRoomReservations.stream()
                .map(roomReservationConverter::toResponseDto).collect(Collectors.toList());
        return ResponseEntity.ok(reservationResponseDTOS);
    }

}
