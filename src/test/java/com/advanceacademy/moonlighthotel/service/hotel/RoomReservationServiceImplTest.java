package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomReservationRepository;
import com.advanceacademy.moonlighthotel.service.hotel.impl.RoomReservationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomReservationServiceImplTest {

    @InjectMocks
    private RoomReservationServiceImpl roomReservationService;

    @Mock
    private RoomReservationRepository roomReservationRepository;

    @Test
    public void createRoomReservationWithAvailableRoomTest() {

        // Create a RoomReservation object for testing
        Room room = new Room();
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 5);
        RoomReservation roomReservation = RoomReservation.builder()
                .room(room)
                .startDate(startDate)
                .endDate(endDate).build();

        // Mock the behavior of isRoomAvailable to return true (room is available)
        when(roomReservationRepository.findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(room, startDate, endDate))
                .thenReturn(new ArrayList<>()); // Empty list means room is available
        when(roomReservationRepository.save(roomReservation)).thenReturn(roomReservation);

        // Call the method to create the RoomReservation
        RoomReservation resultRoomReservation = roomReservationService.createRoomReservation(roomReservation);

        // Check if the result matches the saved RoomReservation
        Assertions.assertNotNull(resultRoomReservation);
        Assertions.assertEquals(roomReservation, resultRoomReservation);

        // Verify that save method was called as expected
        verify(roomReservationRepository, times(1)).save(roomReservation);
    }

    @Test
    public void createRoomReservationWithNotAvailableRoom() {

        // Create a RoomReservation object for testing
        Room room = new Room();
        LocalDate startDate = LocalDate.of(2023, 9, 1);
        LocalDate endDate = LocalDate.of(2023, 9, 5);
        RoomReservation roomReservation = RoomReservation.builder()
                .room(room)
                .startDate(startDate)
                .endDate(endDate).build();

        // Mock the behavior of isRoomAvailable to return false (room is not available)
        List<RoomReservation> overlappingReservations = new ArrayList<>();
        overlappingReservations.add(new RoomReservation()); // Adding a reservation means the room is not available
        when(roomReservationRepository.findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(room, startDate, endDate))
                .thenReturn(overlappingReservations);

        // Ensure that an InvalidInputException is thrown
        Assertions.assertThrows(IllegalArgumentException.class, () -> roomReservationService.createRoomReservation(roomReservation));

        // Verify that save method is not called
        verify(roomReservationRepository, never()).save(roomReservation);
    }

    @Test
    public void getRoomReservationByIdWithExistingReservationTest() {

        // Create a RoomReservation object for testing
        Long reservationId = 1L;
        RoomReservation expectedReservation = RoomReservation.builder()
                .id(reservationId).build();


        // Mock the behavior of the repository to return the expected reservation when findById is called
        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.of(expectedReservation));

        RoomReservation actualReservation = roomReservationService.getRoomReservationById(reservationId);

        // Verify that the actualReservation object is not null and is equal to reservationId
        Assertions.assertNotNull(actualReservation);
        Assertions.assertEquals(reservationId, actualReservation.getId());
    }

    @Test
    public void getRoomReservationByIdWithReservationNotFoundTest() {

        Long reservationId = 1L;

        // Mock the behavior of the repository to return an empty Optional when findById is called
        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            roomReservationService.getRoomReservationById(reservationId);
        });
    }

    @Test
    public void getAllRoomReservationsTest() {

        // Create two reservation objects and add them to the expectedReservations collection
        RoomReservation reservation1 = RoomReservation.builder().build();
        RoomReservation reservation2 = RoomReservation.builder().build();
        List<RoomReservation> expectedReservations = Arrays.asList(reservation1, reservation2);

        // Mock the behavior of the repository to return the expected list of reservations when findAll is called
        when(roomReservationRepository.findAll()).thenReturn(expectedReservations);

        List<RoomReservation> actualReservations = roomReservationService.getAllRoomReservations();

        // Verify that the actualReservation object is not null and is equal to expectedReservations
        Assertions.assertNotNull(actualReservations);
        Assertions.assertEquals(expectedReservations.size(), actualReservations.size());
        Assertions.assertTrue(actualReservations.containsAll(expectedReservations));
    }

    @Test
    public void getAllRoomReservationsWithNoReservationTest() {

        // Mock the behavior of the repository to return an empty list when findAll is called
        when(roomReservationRepository.findAll()).thenReturn(Arrays.asList());

        List<RoomReservation> actualReservations = roomReservationService.getAllRoomReservations();

        // Verify that the actualReservation object is not null and that is empty list
        Assertions.assertNotNull(actualReservations);
        Assertions.assertTrue(actualReservations.isEmpty());
    }

    @Test
    public void updateRoomReservationWithExistingReservationTest() {

        Long reservationId = 1L;
        RoomReservation existingReservation = RoomReservation.builder()
                .id(reservationId)
                .build();

        RoomReservation updatedReservation = RoomReservation.builder()
                .id(existingReservation.getId())
                .build();

        // Mock the behavior of the repository to return the existing reservation when findById is called
        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));

        // Mock the behavior of the repository to return the updated reservation when save is called with any argument
        when(roomReservationRepository.save(any(RoomReservation.class))).thenReturn(updatedReservation);

        RoomReservation result = roomReservationService.updateRoomReservation(reservationId, updatedReservation);

        // Verify that the result is not null and that the result id is equal to reservationId
        Assertions.assertNotNull(result);
        Assertions.assertEquals(reservationId, result.getId());
    }

    @Test
    public void updateRoomReservationWhenReservationNotFoundTest() {

        Long reservationId = 1L;
        RoomReservation updatedReservation = new RoomReservation();
        updatedReservation.setId(reservationId);

        // Mock the behavior of the repository to return an empty Optional when findById is called
        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> roomReservationService.updateRoomReservation(reservationId, updatedReservation));
    }

    @Test
    public void deleteRoomReservationByIdWithExistingReservationTest() {

        Long reservationId = 1L;
        RoomReservation existingReservation = RoomReservation.builder()
                .id(reservationId)
                .build();

        // Mock the behavior of the repository to return the existing reservation when findById is called
        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));

        roomReservationService.deleteRoomReservationById(reservationId);

        // Verify that deleteById will be called once
        verify(roomReservationRepository, times(1)).deleteById(reservationId);
    }

    @Test
    public void deleteRoomReservationByIdWithReservationNotFoundTest() {

        Long reservationId = 1L;

        // Mock the behavior of the repository to return an empty Optional when findById is called
        when(roomReservationRepository.findById(reservationId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class,
                () -> roomReservationService.deleteRoomReservationById(reservationId));

        // Verify that the exception message contains the expected error message
        Assertions.assertTrue(exception.getMessage().contains(String.format("Room reservation with id %d not found", reservationId)));
    }
}
