package com.advanceacademy.moonlighthotel.service.hotel.impl;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomReservationRepository;
import com.advanceacademy.moonlighthotel.service.hotel.RoomReservationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    public RoomReservationServiceImpl(RoomReservationRepository roomReservationRepository) {
        this.roomReservationRepository = roomReservationRepository;
    }

    //Creates a new room reservation in the system
    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public RoomReservation createRoomReservation(RoomReservation roomReservation) {

        Room room = roomReservation.getRoom();
        LocalDate startDate = roomReservation.getStartDate();
        LocalDate endDate = roomReservation.getEndDate();

        // Check if the room is available for the specified dates
        if (!isRoomAvailable(room, startDate, endDate)) {
            throw new IllegalArgumentException(String.format("The room is not available from %s to %s.", startDate, endDate));
        }
        return roomReservationRepository.save(roomReservation);
    }

    //Retrieves a room reservation from the database by its unique identifier
    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public RoomReservation getRoomReservationById(Long id) {
        Optional<RoomReservation> foundRoomReservation = roomReservationRepository.findById(id);
        if (foundRoomReservation.isPresent()) {
            return foundRoomReservation.get();
        } else {
            throw new ResourceNotFoundException(String.format("Room reservation with id %d not found", id));
        }
    }

    //Retrieves a list of all room reservation from the database
    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<RoomReservation> getAllRoomReservations() {
        return roomReservationRepository.findAll();
    }
    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    // Update the attributes of the foundRoomReservation with the new values and then save it
    public RoomReservation updateRoomReservation(Long id, RoomReservation roomReservation) {
        return roomReservationRepository.findById(id)
                .map(foundRoomReservation -> {
                    RoomReservation updatedRoomReservation = RoomReservation.builder()
                            .room(roomReservation.getRoom())
                            .adult(roomReservation.getAdult())
                            .children(roomReservation.getChildren())
                            .user(roomReservation.getUser())
                            .endDate(roomReservation.getEndDate())
                            .startDate(roomReservation.getStartDate())
                            .totalPrice(roomReservation.getTotalPrice())
                            .paymentStatus(roomReservation.getPaymentStatus())
                            .build();

                    updatedRoomReservation.setId(foundRoomReservation.getId());
                    return roomReservationRepository.save(updatedRoomReservation);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Room reservation with id " + id + " not found"));
    }


    //Delete a room reservation by its unique identifier
    @Override
    public void deleteRoomReservationById(Long id) {
        Optional<RoomReservation> foundRoomReservation = roomReservationRepository.findById(id);
        if (foundRoomReservation.isPresent()) {
            roomReservationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Room reservation with id %d not found", id));
        }
    }

    //Helper method used to check if a room is available for booking within a specific date range
    public boolean isRoomAvailable(Room room, LocalDate startDate, LocalDate endDate) {
        List<RoomReservation> overlappingReservations = roomReservationRepository
                .findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(room, startDate, endDate);

        return overlappingReservations.isEmpty();
    }
}
