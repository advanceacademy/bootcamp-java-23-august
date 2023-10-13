package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.entity.barZone.BarZone;
import com.advanceacademy.moonlighthotel.entity.barZone.Screen;

import java.util.List;
import java.util.Optional;

public interface ScreenService {

    //Create a new screen
    Screen createScreen(Screen screen);

    //Get screen by ID
    Screen getScreenById(Long id);

    //Retrieve all screens
    List<Screen> getAllScreens();

    //Update an existing screen
    Screen updateScreen(Long id, Screen screen);

    //Delete screen by ID
    void deleteScreenById(Long id);

    Screen findById(Long screenId);

    Optional<List<Screen>> getScreenByBarZone(BarZone barZone);
}
