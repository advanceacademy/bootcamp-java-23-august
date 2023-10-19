package com.advanceacademy.moonlighthotel.converter.barZone;

import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventResponse;
import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenEventService;
import org.springframework.stereotype.Component;

@Component
public class ScreenEventConverter {

    private final ScreenEventService screenEventService;

    public ScreenEventConverter(ScreenEventService screenEventService) {
        this.screenEventService = screenEventService;
    }

    public ScreenEventResponse response(ScreenEvent screenEvent) {
        Screen screen = Screen.builder()
                .id(screenEvent.getScreen().getId())
                .barZone(screenEvent.getScreen().getBarZone())
                .build();

        return ScreenEventResponse.builder()
                .id(screenEvent.getId())
                .event(screenEvent.getEvent())
                .eventDate(screenEvent.getEventDate())
                .screenId(screen)
                .build();
    }
}
