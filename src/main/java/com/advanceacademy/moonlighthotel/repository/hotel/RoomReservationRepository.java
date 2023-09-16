package com.advanceacademy.moonlighthotel.repository.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation,Long> {

    //A query method that checks for overlapping date ranges.
    List<RoomReservation> findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(
            Room room, LocalDate startDate, LocalDate endDate);
}
