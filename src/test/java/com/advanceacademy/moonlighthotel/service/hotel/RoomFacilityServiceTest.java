package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomFacility;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomFacilityRepository;
import com.advanceacademy.moonlighthotel.service.hotel.impl.RoomFacilityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomFacilityServiceTest {

    @InjectMocks
    private RoomFacilityServiceImpl roomFacilityService;

    @Mock
    private RoomFacilityRepository roomFacilityRepository;

    @Test
    public void createRoomFacilityTest() {

        // Create a RoomFacility object for testing
        RoomFacility roomFacility = RoomFacility.builder()
                .id(1L)
                .facilityName("Wifi")
                .build();

        // Mock the behavior of roomFacilityRepository
        when(roomFacilityRepository.save(roomFacility)).thenReturn(roomFacility);

        RoomFacility createdRoomFacility = roomFacilityService.createRoomFacility(roomFacility);

        // Assert that the created room facility is not null
        Assertions.assertNotNull(createdRoomFacility);

        // Assert that the 'id' of the created room facility matches the expected 'id'
        Assertions.assertEquals(roomFacility.getId(), createdRoomFacility.getId());

        // Assert that the 'facilityName' of the created room facility matches the expected 'facilityName'
        Assertions.assertEquals(roomFacility.getFacilityName(), createdRoomFacility.getFacilityName());

        // Verify that save method was called
        verify(roomFacilityRepository, times(1)).save(roomFacility);
    }

    @Test
    public void getRoomFacilityByIdTest() {

        Long roomFacilityId = 1L;

        // Create an expected RoomFacility with the given ID
        RoomFacility expectedRoomFacility = new RoomFacility();
        expectedRoomFacility.setId(roomFacilityId);

        // Mock the behavior of roomFacilityRepository to return the expected RoomFacility
        when(roomFacilityRepository.findById(roomFacilityId)).thenReturn(Optional.of(expectedRoomFacility));

        // Call the method with the ID
        RoomFacility actualRoomFacility = roomFacilityService.getRoomFacilityById(roomFacilityId);

        // Assert that the actual room facility is not null
        Assertions.assertNotNull(actualRoomFacility);

        // Check if the actual RoomFacility matches the expected one
        Assertions.assertEquals(expectedRoomFacility, actualRoomFacility);

        // Ensure that the repository's findById method was called exactly once with the expected ID
        verify(roomFacilityRepository, times(1)).findById(roomFacilityId);
    }

    @Test
    public void getAllRoomFacilitiesTest() {

        // Create a list of expected RoomFacility objects
        List<RoomFacility> expectedRoomFacilities = new ArrayList<>();
        expectedRoomFacilities.add(new RoomFacility(1L, "WiFi"));
        expectedRoomFacilities.add(new RoomFacility(2L, "TV"));
        expectedRoomFacilities.add(new RoomFacility(3L, "Mini-bar"));

        // Mock the behavior of roomFacilityRepository to return the expected list of RoomFacility objects
        when(roomFacilityRepository.findAll()).thenReturn(expectedRoomFacilities);

        // Call the method to retrieve all room facilities
        List<RoomFacility> actualRoomFacilities = roomFacilityService.getAllRoomFacilities();

        // Check if the actual list is not null and contains the same elements as the expected list
        Assertions.assertNotNull(actualRoomFacilities);
        Assertions.assertEquals(expectedRoomFacilities, actualRoomFacilities);

        // Ensure that the repository's findAll method was called exactly once
        verify(roomFacilityRepository, times(1)).findAll();
    }

    @Test
    public void updateRoomFacilityWithExistingFacilityTest() {

        // Define an existing RoomFacility with ID 1
        Long roomFacilityId = 1L;
        RoomFacility existingRoomFacility = new RoomFacility(roomFacilityId, "WiFi");

        // Create an updated RoomFacility with the same ID and a new name
        RoomFacility updatedRoomFacility = new RoomFacility(roomFacilityId, "High-Speed WiFi");

        // Mock the behavior of roomFacilityRepository to return the existing RoomFacility when findById is called
        when(roomFacilityRepository.findById(roomFacilityId)).thenReturn(Optional.of(existingRoomFacility));

        // Mock the behavior of roomFacilityRepository's save method
        when(roomFacilityRepository.save(any(RoomFacility.class))).thenReturn(updatedRoomFacility);

        // Call the method to update the RoomFacility
        RoomFacility resultRoomFacility = roomFacilityService.updateRoomFacility(roomFacilityId, updatedRoomFacility);

        // Check if the result matches the updated RoomFacility
        Assertions.assertNotNull(resultRoomFacility);
        Assertions.assertEquals(updatedRoomFacility, resultRoomFacility);

        // Verify that findById and save methods were called as expected
        verify(roomFacilityRepository, times(1)).findById(roomFacilityId);
        verify(roomFacilityRepository, times(1)).save(any(RoomFacility.class));
    }

    @Test
    public void updateRoomFacilityWithInvalidIdTest() {

        // Define an invalid RoomFacility ID (not present in the repository)
        Long roomFacilityId = 2L;

        // Mock the behavior of roomFacilityRepository to return an empty Optional
        when(roomFacilityRepository.findById(roomFacilityId)).thenReturn(Optional.empty());

        // Attempt to update the RoomFacility with the invalid ID and catch the exception
        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> roomFacilityService.updateRoomFacility(roomFacilityId, new RoomFacility())
        );

        // Verify that the exception message contains the expected error message
        Assertions.assertTrue(exception.getMessage().contains("Room facility with id 2 not found"));

        // Verify that findById was called as expected
        verify(roomFacilityRepository, times(1)).findById(roomFacilityId);

        // Ensure that save method was not called
        verify(roomFacilityRepository, never()).save(any(RoomFacility.class));
    }

    @Test
    public void deleteRoomFacilityByIdTest() {

        // Define an existing RoomFacility with ID 1
        Long roomFacilityId = 1L;
        RoomFacility existingRoomFacility = new RoomFacility(roomFacilityId, "WiFi");

        // Mock the behavior of roomFacilityRepository to return the existing RoomFacility when findById is called
        when(roomFacilityRepository.findById(roomFacilityId)).thenReturn(Optional.of(existingRoomFacility));

        // Call the method to delete the RoomFacility by ID
        roomFacilityService.deleteRoomFacilityById(roomFacilityId);

        // Ensure that findById and deleteById methods were called
        verify(roomFacilityRepository, times(1)).findById(roomFacilityId);
        verify(roomFacilityRepository, times(1)).deleteById(roomFacilityId);
    }
}
