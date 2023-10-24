package com.advanceacademy.moonlighthotel.dto.barZone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenEventDTO {

    @NotNull
    @NotBlank(message = "Event name may not be blank")
    private String event;

    @NotNull
    @PastOrPresent(message = "Event date must be in the past or present")
    private LocalDate eventDate;

    @NotNull
    @Positive(message = "Screen ID must be a positive number")
    private Long screenId;
}
