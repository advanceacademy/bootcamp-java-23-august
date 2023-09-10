package com.advanceacademy.moonlighthotel.service.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import com.advanceacademy.moonlighthotel.service.hotel.impl.RoomServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @InjectMocks
    private RoomServiceImpl roomService;

    @Mock
    private RoomRepository roomRepository;

    @Test
    public void createRoomTest() {

        // Create a Room object for testing
        Room room = Room.builder()
                .id(1L)
                .roomNumber(5)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .roomFacilities(new HashSet<>())
                .reservations(new ArrayList<>())
                .build();

        // Mock the behavior of roomRepository.save() to return the room object
        given(roomRepository.save(any(Room.class))).willReturn(room);

        // Call the method under test
        Room createdRoom = roomService.createRoom(room);

        // Verify that roomRepository.save() was called once with the room object
        verify(roomRepository, times(1)).save(room);

        // Verify that the returned room object is not null
        Assertions.assertNotNull(createdRoom);
    }

    @Test
    public void checkForDuplicateRoomNumberWhenCreateRoomTest() {

        // Create a Room object for testing
        Room room = Room.builder()
                .id(1L)
                .roomNumber(5)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .roomFacilities(new HashSet<>())
                .reservations(new ArrayList<>())
                .build();

        // Mock the behavior of roomRepository.save() to throw a DataIntegrityViolationException
        given(roomRepository.save(any(Room.class)))
                .willThrow(new DataIntegrityViolationException(String.format("Room with number %d already exists.", room.getRoomNumber())));

        // Call the method under test and expect it to throw a DuplicateRecordException
        Assertions.assertThrows(DuplicateRecordException.class, () -> roomService.createRoom(room));

        // Verify that roomRepository.save() was called once with the room object
        verify(roomRepository, times(1)).save(room);
    }

    @Test
    public void getRoomByIdTest() {

        // Define the ID of the room we want to retrieve
        Long roomId = 1L;

        // Create an instance of a room with the expected ID
        Room room = Room.builder()
                .id(roomId).build();

        // Mock the behavior of the roomRepository when findById is called with the room's ID
        // It should return an Optional containing the expected room.
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Call the getRoomById method with the room's ID.
        Room actualRoom = roomService.getRoomById(roomId);

        // Check that the actualRoom is not null, indicating that a room was found
        Assertions.assertNotNull(actualRoom);

        // Check that the ID of the actualRoom matches the expected ID
        Assertions.assertEquals(roomId, actualRoom.getId());

        // Verify that the findById method of the roomRepository was called exactly once with the expected ID
        verify(roomRepository, times(1)).findById(roomId);
    }

    @Test
    public void getAllRoomsTest() {

        // Create two Room object for testing
        Room room1 = Room.builder()
                .id(1L).build();
        Room room2 = Room.builder()
                .id(2L).build();

        // Create a list of expected rooms
        List<Room> expectedRooms = Arrays.asList(room1, room2);

        // Mock the behavior of the roomRepository
        when(roomRepository.findAll()).thenReturn(expectedRooms);

        // Call the service method to get actual rooms
        List<Room> actualRooms = roomService.getAllRooms();

        // Check that the actual list size matches the expected size
        Assertions.assertEquals(expectedRooms.size(), actualRooms.size());
        // Iterate through the lists and check that IDs match
        for (int i = 0; i < expectedRooms.size(); i++) {
            Assertions.assertEquals(expectedRooms.get(i).getId(), actualRooms.get(i).getId());
            // You can add more assertions here for other attributes if needed, but we have to add those attributes to Room objects when we created them
        }
    }

    @Test
    public void updateRoomWithExistingRoomTest() {

        Long roomId = 1L;
        Room existingRoom = Room.builder()
                .id(roomId
                ).build();

        Room updatedRoom = Room.builder()
                .id(existingRoom.getId())
                .roomNumber(101
                ).build();

        // Mock the behavior of roomRepository
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(existingRoom));

        // Use doReturn().when() to stub the save method with any Room argument
        doReturn(updatedRoom).when(roomRepository).save(any(Room.class));

        Room resultRoom = roomService.updateRoom(roomId, updatedRoom);

        // Check if the 'id' property of the 'resultRoom' matches the 'id' property of the 'updatedRoom'
        Assertions.assertEquals(updatedRoom.getId(), resultRoom.getId());

        // Check if the 'roomNumber' property of the 'resultRoom' matches the 'roomNumber' property of the 'updatedRoom'
        Assertions.assertEquals(updatedRoom.getRoomNumber(), resultRoom.getRoomNumber());

        // Verify that findById and save methods were called
        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void updateRoomWhenRoomNotExistTest() {

        Long roomId = 1L;
        Room updatedRoom = new Room();
        updatedRoom.setId(roomId);

        // Mock the behavior of roomRepository to return an empty Optional
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        // Expect a ResourceNotFoundException to be thrown
        Assertions.assertThrows(ResourceNotFoundException.class, () -> roomService.updateRoom(roomId, updatedRoom));

        // Verify that findById was called
        verify(roomRepository, times(1)).findById(roomId);

        // Verify that save was not called
        verify(roomRepository, never()).save(updatedRoom);
    }

    @Test
    public void deleteRoomByIdTest() {

        Long roomId = 1L;
        Room room = Room.builder()
                .id(roomId)
                .build();

        when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));

        // Mock the behavior of the roomRepository
        roomService.deleteRoomById(roomId);

        // Verify that the deleteById method is called on the repository
        verify(roomRepository, times(1)).deleteById(roomId);
    }
}
