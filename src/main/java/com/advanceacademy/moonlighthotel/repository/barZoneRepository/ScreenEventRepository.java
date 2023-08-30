package com.advanceacademy.moonlighthotel.repository.barZoneRepository;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenEventRepository extends JpaRepository<ScreenEvent, Long> {

    Optional<List<ScreenEvent>> findByEventDate(ScreenEvent eventDate);
    Optional<List<ScreenEvent>> findByScreen(Screen screen);

}
