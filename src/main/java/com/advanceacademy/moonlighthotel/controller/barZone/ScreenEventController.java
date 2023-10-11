package com.advanceacademy.moonlighthotel.controller.barZone;

import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventDTO;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/screen-events")
@PreAuthorize("hasRole('ADMIN')") // Only admin can access these endpoints
public class ScreenEventController {
    private final ScreenEventService screenEventService;

    @Autowired
    public ScreenEventController(ScreenEventService screenEventService) {
        this.screenEventService = screenEventService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')") // Only admin can access these endpoints
    public ResponseEntity<ScreenEvent> createScreenEvent(@RequestBody ScreenEventDTO eventDTO) {
        try {
            ScreenEvent createdEvent = screenEventService.createScreenEventWithCheck(eventDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
        } catch (DuplicateRecordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
