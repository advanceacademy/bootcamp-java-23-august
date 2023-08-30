package com.advanceacademy.moonlighthotel.repository.hotel;

import com.advanceacademy.moonlighthotel.entity.hotel.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility,Long> {
}
