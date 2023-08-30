package com.advanceacademy.moonlighthotel.repository.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation,Long> {

}
