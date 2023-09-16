package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenSeatRepository;
import com.advanceacademy.moonlighthotel.service.barZone.impl.ScreenSeatServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScreenSeatServiceImplTest {

    @InjectMocks
    private ScreenSeatServiceImpl screenSeatService;

    @Mock
    private ScreenSeatRepository screenSeatRepository;

    //TODO create test for creating a screen seat

    //TODO create test for get a screen seat

    //TODO create test for getAll a screen seats

    //TODO create test for updating a screen seat

    //TODO create test for delete a screen seat
}
