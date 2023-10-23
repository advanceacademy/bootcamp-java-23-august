package com.advanceacademy.moonlighthotel.converter.hotel;

import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationRequestDTO;
import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationResponseDTO;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.service.hotel.RoomReservationService;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoomReservationConverter {
    private final RoomReservationService roomReservationService;
    private final RoomService roomService;

    @Autowired
    public RoomReservationConverter(RoomReservationService roomReservationService, RoomService roomService) {
        this.roomReservationService = roomReservationService;
        this.roomService = roomService;
    }

    public RoomReservation toRoomReservation(RoomReservationRequestDTO requestDto) {
        // Validate the request DTO
        if (requestDto == null) {
            throw new IllegalArgumentException("Room reservation request DTO is required.");
        }

        Long roomId = requestDto.getRoomId();
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID is required.");
        }

        Optional<Room> roomOptional = Optional.ofNullable(roomService.getRoomById(roomId));
        Room room = roomOptional.orElseThrow(() -> new IllegalArgumentException("Room not found."));

        return RoomReservation.builder().startDate(requestDto.getStartDate()).endDate(requestDto.getEndDate()).adult(requestDto.getAdult()).children(requestDto.getChildren()).room(room).paymentStatus(PaymentStatus.PAID).build();
    }

    public RoomReservationResponseDTO toResponseDto(RoomReservation roomReservation) {
        // Validate the input entity
        if (roomReservation == null) {
            throw new IllegalArgumentException("Room reservation entity is required.");
        }

        Room room = roomReservation.getRoom();
        if (room == null) {
            throw new IllegalArgumentException("Room is missing for the room reservation.");
        }

        return RoomReservationResponseDTO.builder().id(roomReservation.getId()).startDate(roomReservation.getStartDate()).endDate(roomReservation.getEndDate()).adult(roomReservation.getAdult()).children(roomReservation.getChildren()).totalPrice(roomReservation.getTotalPrice()).userId(roomReservation.getUser() != null ? roomReservation.getUser().getId() : null).roomId(room.getId()).paymentStatus(roomReservation.getPaymentStatus()).build();
    }
}
