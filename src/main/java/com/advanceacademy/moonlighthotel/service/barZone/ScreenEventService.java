package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;

import java.util.List;

public interface ScreenEventService {

    //Create a new screen event
    ScreenEvent createScreenEvent(ScreenEvent ScreenEvent);

    //Get screen event by ID
    ScreenEvent getScreenEventById(Long id);

    //Retrieve all screen events
    List<ScreenEvent> getAllScreenEvents();

    //Update an existing screen event
    ScreenEvent updateScreenEvent(Long id, ScreenEvent ScreenEvent);

    //Delete screen event by ID
    void deleteScreenEventById(Long id);

    ScreenEvent createScreenEventWithCheck(ScreenEvent screenEvent);
}
