package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import com.advanceacademy.moonlighthotel.repository.restaurant.TableReservationRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.impl.TableReservationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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


    @Test
    public void testGetReservationsByUserId() {
        Long userId = 1L;
        List<TableReservation> reservations = new ArrayList<>();
        reservations.add(new TableReservation());
        reservations.add(new TableReservation());

        when(tableReservationRepository.findByUserId(userId)).thenReturn(reservations);

        List<TableReservation> result = tableReservationService.getReservationsByUserId(userId);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetReservationsByTableId() {
        Long tableId = 1L;
        List<TableReservation> reservations = new ArrayList<>();
        reservations.add(new TableReservation());
        reservations.add(new TableReservation());

        when(tableReservationRepository.findByTableId(tableId)).thenReturn(reservations);

        List<TableReservation> result = tableReservationService.getReservationsByTableId(tableId);

        assertEquals(2, result.size());
    }


    @Test
    public void testGetReservationsByDate() {
        LocalDate date = LocalDate.now();
        List<TableReservation> reservations = new ArrayList<>();
        reservations.add(new TableReservation());
        reservations.add(new TableReservation());

        when(tableReservationRepository.findByDate(date)).thenReturn(reservations);

        List<TableReservation> result = tableReservationService.getReservationsByDate(date);

        assertEquals(2, result.size());
    }

}
