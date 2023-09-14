package com.advanceacademy.moonlighthotel.service.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.RestaurantZone;
import com.advanceacademy.moonlighthotel.entity.restaurant.SeatRestaurant;
import com.advanceacademy.moonlighthotel.repository.restaurant.SeatRestaurantRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.impl.SeatRestaurantServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SeatRestaurantServiceTest {

    @InjectMocks
    private SeatRestaurantServiceImpl seatRestaurantService;
    @Mock
    private SeatRestaurantRepository seatRestaurantRepository;

    @Test
    void createSeat() {
        SeatRestaurant seatRestaurant = new SeatRestaurant();
        seatRestaurant.setId(1L);
        seatRestaurant.setZone(RestaurantZone.BAR);

        when(seatRestaurantRepository.save(any(SeatRestaurant.class))).thenReturn(seatRestaurant);

        SeatRestaurant createdSeat = seatRestaurantService.createSeat(seatRestaurant);

        Assertions.assertNotNull(createdSeat);
        Assertions.assertEquals(seatRestaurant, createdSeat);

        verify(seatRestaurantRepository,times(1)).save(seatRestaurant);

        assertEquals(seatRestaurant, createdSeat);
    }

    @Test
    void getSeatById() {
        SeatRestaurant seatRestaurant = new SeatRestaurant();
        seatRestaurant.setId(2L);
        Long seatId = seatRestaurant.getId();

        when(seatRestaurantRepository.findById(seatId)).thenReturn(Optional.of(seatRestaurant));

        SeatRestaurant foundSeatRestaurant = seatRestaurantService.getSeatById(seatId);

        Assertions.assertNotNull(foundSeatRestaurant);
        Assertions.assertEquals(seatRestaurant, foundSeatRestaurant);

        verify(seatRestaurantRepository, times(1)).findById(seatId);

    }
}