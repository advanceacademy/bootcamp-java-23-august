package com.advanceacademy.moonlighthotel.repository.barZoneRepository;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenReservationRepository extends JpaRepository<ScreenReservation, Long> {

    Optional<List<ScreenReservation>> findByReservationDate(ScreenReservation reservation);
    Optional<List<ScreenReservation>> findByScreenEvent(ScreenEvent event);
    Optional<List<ScreenReservation>> findByPaymentStatus(PaymentStatus paymentStatus);
    Optional<List<ScreenReservation>> findByUser(User user);

}
