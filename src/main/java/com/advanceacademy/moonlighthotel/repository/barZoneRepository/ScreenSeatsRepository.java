package com.advanceacademy.moonlighthotel.repository.barZoneRepository;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenReservation;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenSeatsRepository extends JpaRepository<ScreenSeats, Long> {

    Optional<List<ScreenSeats>> findByScreenId(Screen screenId);

}
