package com.advanceacademy.moonlighthotel.converter.hotel;

import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationRequestDTO;
import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationResponseDTO;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.service.hotel.RoomReservationService;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class RoomReservationConverter {
    private final RoomReservationService roomReservationService;
    private final RoomService roomService;
    private final UserService userService ;

    public String authUserEmail ;


    @Autowired
    public RoomReservationConverter(RoomReservationService roomReservationService, RoomService roomService, UserService userService) {
        this.roomReservationService = roomReservationService;
        this.roomService = roomService;
        this.userService = userService;
    }

    public RoomReservation toRoomReservation(RoomReservationRequestDTO requestDto) {
        authUserEmail = userService.getAuthUserEmail();
        User loggedUser = userService.findByEmail(authUserEmail);
        Room requestedRoom = roomService.getRoomByRoomNumber(requestDto.getRoomNumber());
        return RoomReservation.builder()
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .adult(requestDto.getAdult())
                .children(requestDto.getChildren())
                .totalPrice(400.00)
                .user(loggedUser)
                .room(requestedRoom)
                .paymentStatus(PaymentStatus.PAID)
                .build();
    }

    public RoomReservationResponseDTO toResponseDto(RoomReservation roomReservation) {

        return RoomReservationResponseDTO.builder()
                .id(roomReservation.getId())
                .startDate(roomReservation.getStartDate())
                .endDate(roomReservation.getEndDate())
                .adult(roomReservation.getAdult())
                .children(roomReservation.getChildren())
                .totalPrice(roomReservation.getTotalPrice())
                .userId(roomReservation.getUser())
                .roomId(roomReservation.getRoom())
                .paymentStatus(PaymentStatus.PAID)
                .build();
    }
}
