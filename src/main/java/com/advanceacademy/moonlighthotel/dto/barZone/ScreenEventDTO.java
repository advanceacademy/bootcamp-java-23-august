package com.advanceacademy.moonlighthotel.dto.barZone;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenEventDTO {

    @NotNull
    private String event;

    @NotNull
    private LocalDate eventDate;

    @NotNull
    private Long screenId;


    //private Screen screen;

}