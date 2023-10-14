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
import java.util.Optional;

@Component
public class BarZoneCommandRunner implements CommandLineRunner {

    private final ScreenService screenService;
    private final ScreenEventService screenEventService;

    @Autowired
    public BarZoneCommandRunner(ScreenService screenService, ScreenEventService screenEventService) {
        this.screenService = screenService;
        this.screenEventService = screenEventService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Ensure that existing screens are in place
        ensureExistingScreens();

        // Create events for screens if they don't already exist
        //createEventIfNotExists(BarZone.SCREEN_ONE, "Olympic Games Paris 2024", LocalDate.now());
        //  createEventIfNotExists(BarZone.SCREEN_TWO, "Bodybuilding Contest", LocalDate.now().plusDays(1));
        // createEventIfNotExists(BarZone.SCREEN_THREE, "Tyson Fury vs Deontay Wilder", LocalDate.now().plusDays(2));
    }

    private void ensureExistingScreens() {
        List<Screen> allScreens = screenService.getAllScreens();

        if (allScreens.isEmpty()) {
            Screen screen1 = Screen.builder().barZone(BarZone.SCREEN_ONE).build();
            Screen screen2 = Screen.builder().barZone(BarZone.SCREEN_TWO).build();
            Screen screen3 = Screen.builder().barZone(BarZone.SCREEN_THREE).build();

            screenService.createScreen(screen1);
            screenService.createScreen(screen2);
            screenService.createScreen(screen3);
        }
    }

    private void createEventIfNotExists(BarZone barZone, String eventName, LocalDate eventDate) {
        Optional<List<Screen>> screensOptional = screenService.getScreenByBarZone(barZone);

        if (screensOptional.isPresent() && !screensOptional.get().isEmpty()) {
            List<Screen> screens = screensOptional.get();
            Screen screen = screens.get(0);

            if (!screenEventService.screenEventExistsForDate(eventDate)) {
                ScreenEvent event = ScreenEvent.builder().event(eventName).eventDate(eventDate).screen(screen).build();
                screenEventService.createScreenEvent(event);
            }
        } else {
            System.err.println("No screens found for the specified BarZone: " + barZone);
        }
    }
}
