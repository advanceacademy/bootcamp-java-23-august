package com.advanceacademy.moonlighthotel.service.hotel.impl;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.exception.InvalidInputException;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomReservationRepository;
import com.advanceacademy.moonlighthotel.service.hotel.RoomService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomReservationRepository reservationRepository;


    @Autowired
    public RoomServiceImpl(RoomRepository repository, RoomReservationRepository reservationRepository) {
        this.roomRepository = repository;
        this.reservationRepository = reservationRepository;
    }

    //Creates a new room in the system
    @Override
    public Room createRoom(Room room) {
        try {
            return roomRepository.save(room);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateRecordException(String.format("Room with number %d already exists.", room.getRoomNumber()));
        }
    }

    //Retrieves a room from the database by its unique identifier
    @Override
    public Room getRoomById(Long id) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        if (foundRoom.isPresent()){
            return foundRoom.get();
        }else {
            throw new ResourceNotFoundException(String.format("Room with id %d does not exists", id));
        }
    }

    //Retrieves a list of all rooms from the database
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
        List<Room> allRooms = roomRepository.findAll();
        List<RoomReservation> reservations = reservationRepository.findReservationsForPeriod(startDate, endDate);

        List<Room> availableRooms = new ArrayList<>(allRooms);

        for (RoomReservation reservation : reservations) {
            LocalDate reservationStartDate = reservation.getStartDate();
            LocalDate reservationEndDate = reservation.getEndDate();

            if (!(endDate.isBefore(reservationStartDate) || startDate.isAfter(reservationEndDate))) {
                availableRooms.remove(reservation.getRoom());
            }
        }
        return availableRooms;
    }

    @Override
    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, Integer adults) {
        List<Room> allRooms = roomRepository.findAll();
        List<RoomReservation> reservations = reservationRepository.findReservationsForPeriod(startDate, endDate);

        List<Room> availableRooms = new ArrayList<>(allRooms);

        List<Room> availableRoomsByPeople = new ArrayList<>();

        for (RoomReservation reservation : reservations) {
            LocalDate reservationStartDate = reservation.getStartDate();
            LocalDate reservationEndDate = reservation.getEndDate();

            if (!(endDate.isBefore(reservationStartDate) || startDate.isAfter(reservationEndDate))) {
                availableRooms.remove(reservation.getRoom());
            }
        }

        if (adults > 4){
            throw new InvalidInputException("there is no room for " + adults + " people");
        }

        for (Room room : availableRooms){
            Integer maxPeople = room.getMaxPeople();
            if(maxPeople >= adults){
                availableRoomsByPeople.add(room);
            }
        }

        return availableRoomsByPeople;
    }

    @Override
    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, Integer adults, Integer children) {
        List<Room> allRooms = roomRepository.findAll();
        List<RoomReservation> reservations = reservationRepository.findReservationsForPeriod(startDate, endDate);

        List<Room> availableRooms = new ArrayList<>(allRooms);

        List<Room> availableRoomsByPeople = new ArrayList<>();

        for (RoomReservation reservation : reservations) {
            LocalDate reservationStartDate = reservation.getStartDate();
            LocalDate reservationEndDate = reservation.getEndDate();

            if (!(endDate.isBefore(reservationStartDate) || startDate.isAfter(reservationEndDate))) {
                availableRooms.remove(reservation.getRoom());
            }
        }

        if (adults + children > 4){
            throw new InvalidInputException("there is no room for " + (adults + children) + " people");
        }

        for (Room room : availableRooms){
            Integer maxPeople = room.getMaxPeople();
            if(maxPeople >= (adults + children)){
                availableRoomsByPeople.add(room);
            }

        }

        return availableRoomsByPeople;
    }


    // Update the attributes of the foundRoom with the new values and then save it
    @Override
    public Room updateRoom(Long id, Room room) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        if (foundRoom.isPresent()){
            Room updateRoom = foundRoom.get();
            updateRoom.setRoomNumber(room.getRoomNumber());
            updateRoom.setRoomType(room.getRoomType());
            updateRoom.setRoomView(room.getRoomView());
            updateRoom.setRoomPrice(room.getRoomPrice());
            updateRoom.setRoomFacilities(room.getRoomFacilities());
            updateRoom.setReservations(room.getReservations());
            return roomRepository.save(updateRoom);
        }else {
            throw new ResourceNotFoundException(String.format("Room with id %d not found", id));
        }
    }

    //Delete a room by its unique identifier
    @Override
    public void deleteRoomById(Long id) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        if (foundRoom.isPresent()){
            roomRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(String.format("Room with %d not found",id));
        }
    }

    @Override
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return roomRepository.findByRoomType(roomType);
    }

    @Override
    public Room getRoomByRoomNumber(Integer roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

   @Override
    public List<Room> getRoomsByRoomView(RoomView roomView) {
        return roomRepository.findByRoomView((roomView));
    }

    @Override
    public List<Room> getRoomsByPrice(Double price) {
        return roomRepository.findByRoomPrice(price);
    }

    @Override
    public List<Room> getRoomsByMaxPeople(Integer maxPeople) {
        return roomRepository.findByMaxPeople(maxPeople);
    }



}
