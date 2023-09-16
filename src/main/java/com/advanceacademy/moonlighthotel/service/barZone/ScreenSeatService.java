package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenSeat;

import java.util.List;

public interface ScreenSeatService {

    //Create a new screen seat
    ScreenSeat createScreenSeat(ScreenSeat ScreenSeat);

    //Get screen seat by ID
    ScreenSeat getScreenSeatById(Long id);

    //Retrieve all screen seats
    List<ScreenSeat> getAllScreenSeats();

    //Update an existing screen seat
    ScreenSeat updateScreenSeat(Long id, ScreenSeat ScreenSeat);

    //Delete screen seat by ID
    void deleteScreenSeatById(Long id);
}
