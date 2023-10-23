package com.advanceacademy.moonlighthotel.dto.hotel;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RoomReservationRequestDTO {
    @NotNull
    private Long id;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Min(value = 1, message = "Adults must be at least 1")
    private Integer adult;

    @Min(value = 0, message = "Children cannot be negative")
    private Integer children;

    private Double totalPrice;

    @NotNull
    private Long userId;

    @NotNull
    private Long roomId;

    @NotNull
    private PaymentStatus paymentStatus;
}
