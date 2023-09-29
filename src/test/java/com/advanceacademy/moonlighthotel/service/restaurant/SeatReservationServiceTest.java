package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.repository.restaurant.SeatReservationRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.impl.SeatReservationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeatReservationServiceTest {

    @InjectMocks
    private SeatReservationServiceImpl seatReservationService;
    @Mock
    private SeatReservationRepository seatReservationRepository;

    @Test
    void createReservation() {
        SeatReservation seatReservation = new SeatReservation();

        when(seatReservationRepository.save(any(SeatReservation.class))).thenReturn(seatReservation);

        SeatReservation createdSeatReservation = seatReservationService.createReservation(seatReservation);

        Assertions.assertNotNull(createdSeatReservation);
        Assertions.assertEquals(seatReservation, createdSeatReservation);

        verify(seatReservationRepository,times(1)).save(seatReservation);
    }

    @Test
    void deleteReservation() {
        SeatReservation seatReservation = new SeatReservation();

        Long reservationId = seatReservation.getId();

        seatReservationService.deleteReservation(reservationId);

        verify(seatReservationRepository, times(1)).deleteById(reservationId);

        assertThat(seatReservationRepository.existsById(reservationId)).isFalse();
    }

    @Test
    void getReservationsByUserId() {
        User user = new User();
        Long userId = user.getId();
        SeatReservation seatReservation = new SeatReservation();
        List<SeatReservation> seatReservations = new ArrayList<>();
        seatReservations.add(seatReservation);

        when(seatReservationRepository.findByUserId(userId)).thenReturn(Optional.of(seatReservations));

        List<SeatReservation> foundSeatReservations = seatReservationService.getReservationsByUserId(userId);

        assertFalse(foundSeatReservations.isEmpty());
        assertEquals(seatReservations, foundSeatReservations);

        verify(seatReservationRepository, times(1)).findByUserId(userId);
    }

    @Test
    void getReservationsByDate() {
        //LocalDate date = LocalDate.parse("2023-02-20");
        SeatReservation seatReservation = new SeatReservation();
        //seatReservation.setDate(date);
        LocalDate date = seatReservation.getDate();
        List<SeatReservation> seatReservations = new ArrayList<>();
        seatReservations.add(seatReservation);

        when(seatReservationRepository.findByDate(date)).thenReturn(Optional.of(seatReservations));

        List<SeatReservation> foundSeatReservations = seatReservationService.getReservationsByDate(date);

        assertFalse(foundSeatReservations.isEmpty());
        assertEquals(seatReservations, foundSeatReservations);

        verify(seatReservationRepository, times(1)).findByDate(date);

    }

}