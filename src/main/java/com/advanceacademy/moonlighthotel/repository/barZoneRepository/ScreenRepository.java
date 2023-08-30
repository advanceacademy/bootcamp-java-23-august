package com.advanceacademy.moonlighthotel.repository.barZoneRepository;

import com.advanceacademy.moonlighthotel.entity.barZone.BarZone;
import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

    Optional<List<Screen>> findByBarZone(BarZone barZone);


}
