package com.advanceacademy.moonlighthotel.runner;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomFacility;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomView;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomFacilityRepository;
import com.advanceacademy.moonlighthotel.repository.hotel.RoomRepository;
import com.advanceacademy.moonlighthotel.service.hotel.impl.RoomServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HotelCommandRunner implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomServiceImpl roomService;


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

        roomService.createRoom(standardSeaRoom1);

        Room standardSeaRoom2 = Room.builder()
              .roomNumber(13)
              .roomType(RoomType.STANDART)
              .roomView(RoomView.SEA)
              .roomPrice(220.00)
              .build();

        roomService.createRoom(standardSeaRoom2);


        //Standard Pool rooms
        Room standardPoolRoom1 = Room.builder()
                .roomNumber(14)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .build();

        roomService.createRoom(standardPoolRoom1);

        Room standardPoolRoom2 = Room.builder()
                .roomNumber(15)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.POOL)
                .roomPrice(220.00)
                .build();

        roomService.createRoom(standardPoolRoom2);


        //Standard Garden rooms
        Room standardGardenRoom1 = Room.builder()
                .roomNumber(16)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        roomService.createRoom(standardGardenRoom1);

        Room standardGardenRoom2 = Room.builder()
                .roomNumber(17)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        roomService.createRoom(standardGardenRoom2);

        Room standardGardenRoom3 = Room.builder()
                .roomNumber(18)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        roomService.createRoom(standardGardenRoom3);

        Room standardGardenRoom4 = Room.builder()
                .roomNumber(19)
                .roomType(RoomType.STANDART)
                .roomView(RoomView.GARDEN)
                .roomPrice(220.00)
                .build();

        roomService.createRoom(standardGardenRoom4);



        //Studios
        //Sea Studios
        Room seaStudio1 = Room.builder()
                .roomNumber(22)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.00)
                .build();

        roomService.createRoom(seaStudio1);

        Room seaStudio2 = Room.builder()
                .roomNumber(23)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.SEA)
                .roomPrice(320.00)
                .build();

        roomService.createRoom(seaStudio2);


        //Pool Studios
        Room poolStudio1 = Room.builder()
                .roomNumber(24)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.00)
                .build();

        roomService.createRoom(poolStudio1);

        Room poolStudio2 = Room.builder()
                .roomNumber(25)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.POOL)
                .roomPrice(320.00)
                .build();

        roomService.createRoom(poolStudio2);


        //Garden Studios
        Room gardenStudio1 = Room.builder()
                .roomNumber(26)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.00)
                .build();

        roomService.createRoom(gardenStudio1);

        Room gardenStudio2 = Room.builder()
                .roomNumber(27)
                .roomType(RoomType.STUDIO)
                .roomView(RoomView.GARDEN)
                .roomPrice(320.00)
                .build();

        roomService.createRoom(gardenStudio2);



        //Apartments
        //Sea Apartments
        Room seaApartment1 = Room.builder()
                .roomNumber(32)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.00)
                .build();

        roomService.createRoom(seaApartment1);

        Room seaApartment2 = Room.builder()
                .roomNumber(33)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.SEA)
                .roomPrice(520.00)
                .build();

        roomService.createRoom(seaApartment2);


        //Pool Apartment
        Room poolApartment1 = Room.builder()
                .roomNumber(34)
                .roomType(RoomType.APARTMENT)
                .roomView(RoomView.POOL)
                .roomPrice(520.00)
                .build();

        roomService.createRoom(poolApartment1);
    }
}
