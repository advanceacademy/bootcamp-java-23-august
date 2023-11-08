package com.advanceacademy.moonlighthotel.repository.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.Room;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation,Long> {

    //A query method that checks for overlapping date ranges.
    List<RoomReservation> findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(
            Room room, LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM RoomReservation r WHERE (r.startDate <= :endDate) AND (r.endDate >= :startDate)")
    List<RoomReservation> findReservationsForPeriod(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    Optional<List<RoomReservation>> findByUser(User user);
}
