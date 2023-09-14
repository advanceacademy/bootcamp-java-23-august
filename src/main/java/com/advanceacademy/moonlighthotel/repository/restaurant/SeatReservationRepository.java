package com.advanceacademy.moonlighthotel.repository.restaurant;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Long> {

    Optional<List<SeatReservation>> findByUserId(Long userId);

    Optional<List<SeatReservation>> findByDate(LocalDate date);

}
