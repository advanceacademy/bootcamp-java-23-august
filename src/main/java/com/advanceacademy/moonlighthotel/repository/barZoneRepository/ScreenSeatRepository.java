package com.advanceacademy.moonlighthotel.repository.barZoneRepository;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenSeatRepository extends JpaRepository<ScreenSeat, Long> {

    Optional<List<ScreenSeat>> findByScreenId(Screen screenId);

}
