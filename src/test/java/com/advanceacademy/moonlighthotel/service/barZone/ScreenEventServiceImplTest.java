package com.advanceacademy.moonlighthotel.service.barZone;

import com.advanceacademy.moonlighthotel.entity.barZone.BarZone;
import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenEventRepository;
import com.advanceacademy.moonlighthotel.service.barZone.impl.ScreenEventServiceImpl;
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

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScreenEventServiceImplTest {

    @InjectMocks
    private ScreenEventServiceImpl screenEventService;

    @Mock
    private ScreenEventRepository screenEventRepository;

    @Test
    void createScreenEventWithNoDuplicatesTest() {
        // Create a sample ScreenEvent
        ScreenEvent newScreenEvent = ScreenEvent.builder()
                .event("Box fight")
                .eventDate(LocalDate.of(2023, 9, 15))
                .screen(Screen.builder()
                        .barZone(BarZone.SCREEN_ONE)
                        .build())
                .build();

        // Create a mock list of existing ScreenEvents with no duplicates
        List<ScreenEvent> existingScreenEvents = new ArrayList<>();

        // Configure the behavior of the mock repository
        when(screenEventRepository.findAll()).thenReturn(existingScreenEvents);
        when(screenEventRepository.save(newScreenEvent)).thenReturn(newScreenEvent);

        // Call the method being tested
        ScreenEvent createdScreenEvent = screenEventService.createScreenEvent(newScreenEvent);

        // Verify that the ScreenEvent is saved
        verify(screenEventRepository).save(newScreenEvent);

        // Assert that the created ScreenEvent matches the expected one
        Assertions.assertEquals(newScreenEvent, createdScreenEvent);
    }

    @Test
    void createScreenEventWithDuplicatesTest() {
        // Create a sample ScreenEvent with duplicate data
        ScreenEvent newScreenEvent = ScreenEvent.builder()
                .screen(Screen.builder()
                        .barZone(BarZone.SCREEN_ONE)
                        .build())
                .eventDate(LocalDate.of(2023, 9, 15))
                .event("Boxing match").build();

        // Create a mock list of existing ScreenEvents with duplicates
        List<ScreenEvent> existingScreenEvents = new ArrayList<>();
        existingScreenEvents.add(newScreenEvent);

        // Configure the behavior of the mock repository
        when(screenEventRepository.findAll()).thenReturn(existingScreenEvents);

        // Call the method being tested and expect an exception
        Assertions.assertThrows(DuplicateRecordException.class, () -> {
            screenEventService.createScreenEvent(newScreenEvent);
        });

        // Verify that save method was not called in case of duplicates
        verify(screenEventRepository, never()).save(newScreenEvent);
    }

    @Test
    void getScreenEventByIdWhenExistsTest() {
        // Arrange
        Long id = 1L;
        ScreenEvent expectedScreenEvent = ScreenEvent.builder()
                .id(id)
                .event("Boxing match")
                .build();

        // Mock the behavior of the repository when findById is called with id
        when(screenEventRepository.findById(id)).thenReturn(Optional.of(expectedScreenEvent));

        ScreenEvent resultScreenEvent = screenEventService.getScreenEventById(id);

        Assertions.assertEquals(expectedScreenEvent, resultScreenEvent, "Returned ScreenEvent should match the expected one");

        // Verify that the findById method of the repository was called with the expected id
        verify(screenEventRepository, times(1)).findById(id);
    }

    @Test
    void getScreenEventByIdWhenNotExistsTest() {

        Long id = 2L;

        // Mock the behavior of the repository when findById is called with id
        when(screenEventRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            screenEventService.getScreenEventById(id);
        }, "Should throw ResourceNotFoundException when ScreenEvent is not found");

        // Verify that the findById method of the repository was called with the expected id
        verify(screenEventRepository, times(1)).findById(id);
    }

    @Test
    void getAllScreenEventsWhenEventsExistTest () {

        ScreenEvent event1 = ScreenEvent.builder()
                .id(1L)
                .event("Boxing match")
                .build();
        ScreenEvent event2 = ScreenEvent.builder()
                .id(2L)
                .event("Football match")
                .build();
        List<ScreenEvent> expectedEvents = Arrays.asList(event1, event2);

        // Mock the behavior of the repository when findAll is called
        when(screenEventRepository.findAll()).thenReturn(expectedEvents);

        List<ScreenEvent> resultEvents = screenEventService.getAllScreenEvents();

        Assertions.assertEquals(expectedEvents.size(), resultEvents.size(), "Returned list size should match the expected size");
        Assertions.assertTrue(resultEvents.containsAll(expectedEvents), "Returned list should contain all expected events");

        // Verify that the findAll method of the repository was called exactly once
        verify(screenEventRepository, times(1)).findAll();
    }

//    @Test
//    public void updateScreenEventWhenSuccessTest() {
//        // Create a sample ScreenEvent with an ID
//        Long screenEventId = 1L;
//
//        ScreenEvent existingScreenEvent = new ScreenEvent();
//        existingScreenEvent.setId(screenEventId);
//        existingScreenEvent.setEvent("Old Event");
//        existingScreenEvent.setScreen(Screen.builder().barZone(BarZone.SCREEN_ONE).build());
//        existingScreenEvent.setEventDate(LocalDate.of(2023, 9, 13));
//
//        // Create a request to update the ScreenEvent
//        ScreenEvent request = new ScreenEvent();
//        request.setEvent("New Event");
//        request.setScreen(Screen.builder().barZone(BarZone.SCREEN_TWO).build());
//        request.setEventDate(LocalDate.of(2023, 9, 14));
//
//        // Mock the behavior of the repository when findById is called
//        when(screenEventRepository.findById(screenEventId)).thenReturn(Optional.of(existingScreenEvent));
//
//        // Call the updateScreenEvent method
//        ScreenEvent updatedScreenEvent = screenEventService.updateScreenEvent(screenEventId, request);
//
//        // Verify that the repository's save method was called with the updated ScreenEvent
//        verify(screenEventRepository, times(1)).save(existingScreenEvent);
//
//        // Assertions
//        Assertions.assertEquals("New Event", updatedScreenEvent.getEvent());
//        Assertions.assertEquals(BarZone.SCREEN_TWO, updatedScreenEvent.getScreen().getBarZone()); // Check the enum value
//        Assertions.assertEquals(LocalDate.of(2023, 9, 14), updatedScreenEvent.getEventDate());
//    }

    @Test
    public void updateScreenEventWhenNotExistTest() {
        // Specify an ID that won't exist in the repository
        Long nonExistentId = 999L;

        // Create a request to update the ScreenEvent
        ScreenEvent request = ScreenEvent.builder()
                .event("Boxing match")
                .screen(Screen.builder()
                        .barZone(BarZone.SCREEN_ONE)
                        .build())
                .eventDate(LocalDate.of(2023, 9, 14))
                .build();

        // Mock the behavior of the repository when findById is called for a non-existent ID
        when(screenEventRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        try {
            // Call the updateScreenEvent method, expecting a ResourceNotFoundException
            screenEventService.updateScreenEvent(nonExistentId, request);

            // If the exception is not thrown, fail the test
            fail("Expected ResourceNotFoundException, but it was not thrown.");
        } catch (ResourceNotFoundException e) {
            // If the exception is thrown, the test passes
            // You can add further assertions or checks if needed
        }
    }

    @Test
    public void updateScreenEventNullEventTest() {
        // Create a sample ScreenEvent
        Long screenEventId = 1L;
        ScreenEvent existingScreenEvent = ScreenEvent.builder()
                .id(screenEventId)
                .event("Boxing match")
                .screen(Screen.builder()
                        .barZone(BarZone.SCREEN_ONE)
                        .build())
                .eventDate(LocalDate.of(2023, 9, 13))
                .build();

        // Create a request with a null event field
        ScreenEvent request = ScreenEvent.builder()
                .event(null)
                .screen(Screen.builder()
                        .barZone(BarZone.SCREEN_TWO)
                        .build())
                .eventDate(LocalDate.of(2023, 9, 14))
                .build();

        // Mock the behavior of the repository when findById is called
        when(screenEventRepository.findById(screenEventId)).thenReturn(Optional.of(existingScreenEvent));

        try {
            // Call the updateScreenEvent method, expecting an IllegalArgumentException
            screenEventService.updateScreenEvent(screenEventId, request);

            // If the exception is not thrown, fail the test
            fail("Expected IllegalArgumentException, but it was not thrown.");
        } catch (IllegalArgumentException e) {
            // If the exception is thrown, the test passes
            // You can add further assertions or checks if needed
        }
    }

    @Test
    public void deleteScreenEventByIdWhenSuccessTest() {
        // Specify an ID that exists in the repository
        Long screenEventId = 1L;

        // Mock the behavior of the repository when findById is called
        when(screenEventRepository.findById(screenEventId)).thenReturn(Optional.of(new ScreenEvent()));

        // Call the deleteScreenEventById method
        screenEventService.deleteScreenEventById(screenEventId);

        // Verify that the repository's deleteById method was called with the specified ID
        verify(screenEventRepository, times(1)).deleteById(screenEventId);
    }

    @Test
    public void deleteScreenEventByIdWhenResourceNotFoundTest() {

        // Specify an ID that won't exist in the repository
        Long nonExistentId = 999L;

        // Mock the behavior of the repository when findById is called for a non-existent ID
        when(screenEventRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        try {
            // Call the deleteScreenEventById method, expecting a ResourceNotFoundException
            screenEventService.deleteScreenEventById(nonExistentId);

            // If the exception is not thrown, fail the test
            fail("Expected ResourceNotFoundException, but it was not thrown.");
        } catch (ResourceNotFoundException e) {
            // If the exception is thrown, the test passes
            // You can add further assertions or checks if needed
        }
    }
}
