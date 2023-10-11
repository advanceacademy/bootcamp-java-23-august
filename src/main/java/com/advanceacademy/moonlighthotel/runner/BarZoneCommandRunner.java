package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.barZone.BarZone;
import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenEventService;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class BarZoneCommandRunner implements CommandLineRunner {

    private final ScreenService screenService;
    private final ScreenEventService screenEventService ;

    @Autowired
    public BarZoneCommandRunner(ScreenService screenService,ScreenEventService screenEventService) {
        this.screenService = screenService;
        this.screenEventService = screenEventService ;
    }

    @Override
    public void run(String... args) throws Exception {
        Screen screen1 = Screen.builder().barZone(BarZone.SCREEN_ONE).build();
        Screen screen2 = Screen.builder().barZone(BarZone.SCREEN_TWO).build();
        Screen screen3 = Screen.builder().barZone(BarZone.SCREEN_THREE).build();

        List<Screen> allScreens = screenService.getAllScreens();

        if (allScreens.isEmpty()) {
            screenService.createScreen(screen1);
            screenService.createScreen(screen2);
            screenService.createScreen(screen3);
        }

        // Check if an event already exists for the specified date
        LocalDate eventDate = LocalDate.now();

        if (!screenEventService.screenEventExistsForDate(eventDate)) {
            ScreenEvent olympicGamesEvent = ScreenEvent.builder()
                    .event("Olympic games Paris 2024")
                    .eventDate(eventDate)
                    .screen(screen1)
                    .build();

            screenEventService.createScreenEvent(olympicGamesEvent);
        }

        LocalDate eventDate2 = LocalDate.now().plusDays(1);

        if (!screenEventService.screenEventExistsForDate(eventDate2)) {
            ScreenEvent bodybuildingEvent = ScreenEvent.builder()
                    .event("Bodybuilding Contest")
                    .eventDate(eventDate2)
                    .screen(screen2)
                    .build();

            screenEventService.createScreenEvent(bodybuildingEvent);
        }

        LocalDate eventDate3 = LocalDate.now().plusDays(2);

        if (!screenEventService.screenEventExistsForDate(eventDate3)) {
            ScreenEvent boxingEvent = ScreenEvent.builder()
                    .event("Tyson Fury vs Deontay Wilder")
                    .eventDate(eventDate3)
                    .screen(screen3)
                    .build();

            screenEventService.createScreenEvent(boxingEvent);
        }
    }
}
