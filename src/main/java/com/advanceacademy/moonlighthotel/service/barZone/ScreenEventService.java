package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.dto.barZone.ScreenEventDTO;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;

import java.time.LocalDate;
import java.util.List;

public interface ScreenEventService {

    //Create a new screen event
    ScreenEvent createScreenEvent(ScreenEvent ScreenEvent);

    ScreenEvent getEventByName(String eventName);

    List<ScreenEvent> getByEventsDate(LocalDate eventsDate);

    //Get screen event by ID
    ScreenEvent getScreenEventById(Long id);

    //Retrieve all screen events
    List<ScreenEvent> getAllScreenEvents();

    //Update an existing screen event
    ScreenEvent updateScreenEvent(Long id, ScreenEvent ScreenEvent);

    //Delete screen event by ID
    void deleteScreenEventById(Long id);



    //@PreAuthorize("hasRole('ADMIN')") // Only admin can add Screen events
    //ScreenEvent createScreenEventWithCheck(ScreenEventDTO eventDTO) throws DuplicateRecordException;

    //@PreAuthorize("hasRole('ADMIN')") // Only admin can add Screen events
    ScreenEvent createScreenEventWithCheck(ScreenEventDTO eventDTO) throws DuplicateRecordException;

    long countScreenEventsByEventDate(LocalDate eventDate);

    boolean screenEventExistsForDate(LocalDate eventDate);
}
