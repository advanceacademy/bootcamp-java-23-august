package com.advanceacademy.moonlighthotel.repository.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByRoomType(RoomType roomType);
    Room findByRoomNumber(Integer roomNumber);
    List<Room> findByRoomView(String roomView);
    List<Room> findByRoomPrice(Double price);

}
