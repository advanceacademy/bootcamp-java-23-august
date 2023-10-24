package com.advanceacademy.moonlighthotel.dto.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableRestaurant;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TableReservationRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    private LocalTime hour;

    @NotNull
    private Integer tableNumber;

    @Min(value = 1)
    private Integer numberOfPeople;
}
