package com.advanceacademy.moonlighthotel.service;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.repository.TableReservationRepository;
import com.advanceacademy.moonlighthotel.service.implementation.TableReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class TableReservationServiceImplTest {

   @Mock
    private TableReservationRepository tableReservationRepository;

    @InjectMocks
    private TableReservationServiceImpl tableReservationService ;


    @Test
    public void testCreateReservation() {

        TableReservation reservation = new TableReservation();
        reservation.setPrice(777.00);
        reservation.setCountPeople(255);

        when(tableReservationRepository.save(any(TableReservation.class))).thenReturn(reservation);

        TableReservation createdReservation = tableReservationService.createReservation(reservation);

        assertNotNull(createdReservation);

        assertEquals(777.00,createdReservation.getPrice());
        assertEquals(255,createdReservation.getCountPeople());

        verify(tableReservationRepository, times(1)).save(any(TableReservation.class));

    }


    @Test
    public void testDeleteReservation() {

        Long reservationId = 1L;

        tableReservationService.deleteReservation(reservationId);

        verify(tableReservationRepository, times(1)).deleteById(reservationId);
    }
}
