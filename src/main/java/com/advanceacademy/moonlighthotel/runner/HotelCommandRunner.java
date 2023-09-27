package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
<<<<<<< HEAD
import com.advanceacademy.moonlighthotel.entity.hotel.RoomFacility;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomFacilityRepository;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import jakarta.transaction.Transactional;
=======
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import com.advanceacademy.moonlighthotel.service.hotel.impl.RoomServiceImpl;
>>>>>>> origin/main
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

<<<<<<< HEAD
import java.util.HashSet;
import java.util.Set;
=======
import java.util.List;
>>>>>>> origin/main

@Component
public class HotelCommandRunner implements CommandLineRunner {

    @Autowired
<<<<<<< HEAD
    RoomFacilityRepository roomFacilityRepository;

    @Autowired
    RoomRepository roomRepository;

=======
    RoomRepository roomRepository;

    @Autowired
    RoomServiceImpl roomService;

    public boolean isRoomNumberUnique(Room actual) {
        List<Room> allByRoomNumber = roomRepository.findAll();

        boolean isUnique = allByRoomNumber.stream()
                .noneMatch(room -> room.getRoomNumber().equals(actual.getRoomNumber()));

        return isUnique;
    }
>>>>>>> origin/main

    @Override
    public void run(String... args) throws Exception {

<<<<<<< HEAD
        //Standard rooms
        //Sea Standard rooms
        Room standardSeaRoom1 = Room.builder()
              .roomNumber(12)
              .roomType(RoomType.STANDART)
              .roomView(RoomView.SEA)
              .roomPrice(220.00)
              .build();

        roomRepository.save(standardSeaRoom1);

        Room standardSeaRoom2 = Room.builder()
              .roomNumber(13)
              .roomType(RoomType.STANDART)
              .roomView(RoomView.SEA)
              .roomPrice(220.00)
              .build();

        roomRepository.save(standardSeaRoom2);

=======

        //Standard rooms
        //Sea Standard rooms
        Room standardSeaRoom1 = Room.builder()
                .roomNumber(12)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.SEA)
                .roomPrice(220.00)
                .build();


        if (isRoomNumberUnique(standardSeaRoom1)) {
            roomService.createRoom(standardSeaRoom1);
        }


        Room standardSeaRoom2 = Room.builder()
                .roomNumber(13)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.SEA)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardSeaRoom2)) {
            roomService.createRoom(standardSeaRoom2);
        }
>>>>>>> origin/main

        //Standard Pool rooms
        Room standardPoolRoom1 = Room.builder()
                .roomNumber(14)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(standardPoolRoom1);
=======
        if (isRoomNumberUnique(standardPoolRoom1)) {
            roomService.createRoom(standardPoolRoom1);
        }
>>>>>>> origin/main

        Room standardPoolRoom2 = Room.builder()
                .roomNumber(15)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(standardPoolRoom2);
=======
        if (isRoomNumberUnique(standardPoolRoom2)) {
            roomService.createRoom(standardPoolRoom2);
        }
>>>>>>> origin/main


        //Standard Garden rooms
        Room standardGardenRoom1 = Room.builder()
                .roomNumber(16)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(standardGardenRoom1);
=======
        if (isRoomNumberUnique(standardGardenRoom1)) {
            roomService.createRoom(standardGardenRoom1);
        }
>>>>>>> origin/main

        Room standardGardenRoom2 = Room.builder()
                .roomNumber(17)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(standardGardenRoom2);
=======
        if (isRoomNumberUnique(standardGardenRoom2)) {
            roomService.createRoom(standardGardenRoom2);
        }
>>>>>>> origin/main

        Room standardGardenRoom3 = Room.builder()
                .roomNumber(18)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(standardGardenRoom3);
=======
        if (isRoomNumberUnique(standardGardenRoom3)) {
            roomService.createRoom(standardGardenRoom3);
        }
>>>>>>> origin/main

        Room standardGardenRoom4 = Room.builder()
                .roomNumber(19)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(standardGardenRoom4);

=======
        if (isRoomNumberUnique(standardGardenRoom4)) {
            roomService.createRoom(standardGardenRoom4);
        }
>>>>>>> origin/main


        //Studios
        //Sea Studios
        Room seaStudio1 = Room.builder()
                .roomNumber(22)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(seaStudio1);
=======
        if (isRoomNumberUnique(seaStudio1)) {
            roomService.createRoom(seaStudio1);
        }
>>>>>>> origin/main

        Room seaStudio2 = Room.builder()
                .roomNumber(23)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(seaStudio2);
=======
        if (isRoomNumberUnique(seaStudio2)) {
            roomService.createRoom(seaStudio2);
        }
>>>>>>> origin/main


        //Pool Studios
        Room poolStudio1 = Room.builder()
                .roomNumber(24)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(poolStudio1);
=======
        if (isRoomNumberUnique(poolStudio1)) {
            roomService.createRoom(poolStudio1);
        }
>>>>>>> origin/main

        Room poolStudio2 = Room.builder()
                .roomNumber(25)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(poolStudio2);
=======
        if (isRoomNumberUnique(poolStudio2)) {
            roomService.createRoom(poolStudio2);
        }
>>>>>>> origin/main

        //Garden Studios
        Room gardenStudio1 = Room.builder()
                .roomNumber(26)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(gardenStudio1);
=======
        if (isRoomNumberUnique(gardenStudio1)) {
            roomService.createRoom(gardenStudio1);
        }
>>>>>>> origin/main

        Room gardenStudio2 = Room.builder()
                .roomNumber(27)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(gardenStudio2);

=======
        if (isRoomNumberUnique(gardenStudio2)) {
            roomService.createRoom(gardenStudio2);
        }
>>>>>>> origin/main


        //Apartments
        //Sea Apartments
        Room seaApartment1 = Room.builder()
                .roomNumber(32)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(seaApartment1);
=======
        if (isRoomNumberUnique(seaApartment1)) {
            roomService.createRoom(seaApartment1);
        }
>>>>>>> origin/main

        Room seaApartment2 = Room.builder()
                .roomNumber(33)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(seaApartment2);

=======
        if (isRoomNumberUnique(seaApartment2)) {
            roomService.createRoom(seaApartment2);
        }
>>>>>>> origin/main

        //Pool Apartment
        Room poolApartment1 = Room.builder()
                .roomNumber(34)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.POOL)
                .roomPrice(520.00)
                .build();

<<<<<<< HEAD
        roomRepository.save(poolApartment1);
=======
        if (isRoomNumberUnique(poolApartment1)) {
            roomService.createRoom(poolApartment1);
        }
>>>>>>> origin/main
    }
}
