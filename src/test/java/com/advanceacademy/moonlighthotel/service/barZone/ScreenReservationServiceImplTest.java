package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenReservationRepository;
import com.advanceacademy.moonlighthotel.service.barZone.impl.ScreenReservationServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ScreenReservationServiceImplTest {

    @InjectMocks
    private ScreenReservationServiceImpl screenReservationService;

    @Mock
    private ScreenReservationRepository screenReservationRepository;

    //TODO create test for creating a screen reservation

    //TODO create test for get a screen reservation

    //TODO create test for getAll a screen reservations

    //TODO create test for updating a screen reservation

    //TODO create test for delete a screen reservation


}
