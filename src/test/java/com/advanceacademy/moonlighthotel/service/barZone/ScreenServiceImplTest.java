package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenRepository;
import com.advanceacademy.moonlighthotel.service.barZone.impl.ScreenServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScreenServiceImplTest {

    @InjectMocks
    private ScreenServiceImpl screenService;

    @Mock
    private ScreenRepository screenRepository;

    //TODO create test for creating a screen

    //TODO create test for get a screen

    //TODO create test for getAll a screens

    //TODO create test for updating a screen

    //TODO create test for delete a screen
}
