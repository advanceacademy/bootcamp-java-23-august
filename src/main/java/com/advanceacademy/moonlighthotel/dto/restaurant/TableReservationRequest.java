package com.advanceacademy.moonlighthotel.dto.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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

    @NotNull
    private LocalTime hour;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RestaurantZone zone;

    @NotNull
    private Boolean isSmoking;

    @NotNull
    private Integer tableNumber;

    @Min(value = 1)
    private Integer numberOfPeople;
}
