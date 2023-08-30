package com.advanceacademy.moonlighthotel.repository.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
