package com.advanceacademy.moonlighthotel.dto.barZone;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class ScreenEventResponse {

    private Long id;

    private String event;

    private LocalDate eventDate;

    private Screen screenId;
}
