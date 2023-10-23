package com.advanceacademy.moonlighthotel.dto.hotel;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
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
    private Long userId;
    private Long roomId;
    private PaymentStatus paymentStatus;
}
