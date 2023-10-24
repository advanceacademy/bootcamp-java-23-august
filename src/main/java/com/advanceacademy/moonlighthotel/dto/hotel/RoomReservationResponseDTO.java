package com.advanceacademy.moonlighthotel.dto.hotel;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.user.User;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomReservationResponseDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer adult;
    private Integer children;
    private Double totalPrice;
    private User userId;
    private Room roomId;
    private PaymentStatus paymentStatus;
}
