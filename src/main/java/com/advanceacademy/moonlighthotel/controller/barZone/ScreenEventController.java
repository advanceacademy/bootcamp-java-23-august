package com.advanceacademy.moonlighthotel.controller.barZone;

import com.advanceacademy.moonlighthotel.converter.barZone.ScreenEventConverter;
import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventDTO;
import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventResponse;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/screen-events")
public class ScreenEventController {
    private final ScreenEventService screenEventService;
    private final ScreenEventConverter screenEventConverter;

    @Autowired
    public ScreenEventController(ScreenEventService screenEventService, ScreenEventConverter screenEventConverter) {
        this.screenEventService = screenEventService;
        this.screenEventConverter = screenEventConverter;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')") // Only administrators can access this endpoint
    public ResponseEntity<ScreenEvent> createScreenEvent(@RequestBody ScreenEventDTO eventDTO) {
        try {
            // Check if the maximum number of events for the day has been reached
            LocalDate eventDate = eventDTO.getEventDate();
            long eventCount = screenEventService.countScreenEventsByEventDate(eventDate);

            if (eventCount < 3) {
                ScreenEvent createdEvent = screenEventService.createScreenEventWithCheck(eventDTO);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (DuplicateRecordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/by-date/{eventDate}")
    public ResponseEntity<List<ScreenEventResponse>> getAllScreenEventsByDate (@PathVariable LocalDate eventDate){
        List<ScreenEvent> foundEvents = screenEventService.getByEventsDate(eventDate);

        if (foundEvents.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<ScreenEventResponse> screenEventResponses = foundEvents.stream()
                .map(screenEventConverter::response)
                .collect(Collectors.toList());
        return ResponseEntity.ok(screenEventResponses);
    }

    @GetMapping("/by-name/{eventName}")
    public ResponseEntity<ScreenEventResponse> getScreenEventByName (@PathVariable String eventName){
        ScreenEvent foundEvent = screenEventService.getEventByName(eventName);
        ScreenEventResponse response = screenEventConverter.response(foundEvent);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-id/{requestId}")
    public ResponseEntity<ScreenEventResponse> getScreenEventById (@PathVariable Long requestId){
        ScreenEvent foundEvent = screenEventService.getScreenEventById(requestId);
        ScreenEventResponse response = screenEventConverter.response(foundEvent);
        return ResponseEntity.ok(response);
    }
}
