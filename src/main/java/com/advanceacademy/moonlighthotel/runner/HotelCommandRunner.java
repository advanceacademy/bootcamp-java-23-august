package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import com.advanceacademy.moonlighthotel.service.hotel.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelCommandRunner implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomServiceImpl roomService;

    public boolean isRoomNumberUnique(Room actual) {
        List<Room> allByRoomNumber = roomRepository.findAll();

        boolean isUnique = allByRoomNumber.stream()
                .noneMatch(room -> room.getRoomNumber().equals(actual.getRoomNumber()));

        return isUnique;
    }

    @Override
    public void run(String... args) throws Exception {


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

        //Standard Pool rooms
        Room standardPoolRoom1 = Room.builder()
                .roomNumber(14)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardPoolRoom1)) {
            roomService.createRoom(standardPoolRoom1);
        }

        Room standardPoolRoom2 = Room.builder()
                .roomNumber(15)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardPoolRoom2)) {
            roomService.createRoom(standardPoolRoom2);
        }


        //Standard Garden rooms
        Room standardGardenRoom1 = Room.builder()
                .roomNumber(16)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardGardenRoom1)) {
            roomService.createRoom(standardGardenRoom1);
        }

        Room standardGardenRoom2 = Room.builder()
                .roomNumber(17)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardGardenRoom2)) {
            roomService.createRoom(standardGardenRoom2);
        }

        Room standardGardenRoom3 = Room.builder()
                .roomNumber(18)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardGardenRoom3)) {
            roomService.createRoom(standardGardenRoom3);
        }

        Room standardGardenRoom4 = Room.builder()
                .roomNumber(19)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        if (isRoomNumberUnique(standardGardenRoom4)) {
            roomService.createRoom(standardGardenRoom4);
        }


        //Studios
        //Sea Studios
        Room seaStudio1 = Room.builder()
                .roomNumber(22)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.00)
                .build();

        if (isRoomNumberUnique(seaStudio1)) {
            roomService.createRoom(seaStudio1);
        }

        Room seaStudio2 = Room.builder()
                .roomNumber(23)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.00)
                .build();

        if (isRoomNumberUnique(seaStudio2)) {
            roomService.createRoom(seaStudio2);
        }


        //Pool Studios
        Room poolStudio1 = Room.builder()
                .roomNumber(24)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.00)
                .build();

        if (isRoomNumberUnique(poolStudio1)) {
            roomService.createRoom(poolStudio1);
        }

        Room poolStudio2 = Room.builder()
                .roomNumber(25)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.00)
                .build();

        if (isRoomNumberUnique(poolStudio2)) {
            roomService.createRoom(poolStudio2);
        }

        //Garden Studios
        Room gardenStudio1 = Room.builder()
                .roomNumber(26)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.00)
                .build();

        if (isRoomNumberUnique(gardenStudio1)) {
            roomService.createRoom(gardenStudio1);
        }

        Room gardenStudio2 = Room.builder()
                .roomNumber(27)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.00)
                .build();

        if (isRoomNumberUnique(gardenStudio2)) {
            roomService.createRoom(gardenStudio2);
        }


        //Apartments
        //Sea Apartments
        Room seaApartment1 = Room.builder()
                .roomNumber(32)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.00)
                .build();

        if (isRoomNumberUnique(seaApartment1)) {
            roomService.createRoom(seaApartment1);
        }

        Room seaApartment2 = Room.builder()
                .roomNumber(33)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.00)
                .build();

        if (isRoomNumberUnique(seaApartment2)) {
            roomService.createRoom(seaApartment2);
        }

        //Pool Apartment
        Room poolApartment1 = Room.builder()
                .roomNumber(34)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.POOL)
                .roomPrice(520.00)
                .build();

        if (isRoomNumberUnique(poolApartment1)) {
            roomService.createRoom(poolApartment1);
        }
    }
}
