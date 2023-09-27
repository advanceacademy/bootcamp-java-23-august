package com.advanceacademy.moonlighthotel.service.restaurant.impl;

import com.advanceacademy.moonlighthotel.entity.restaurant.SeatReservation;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.restaurant.SeatReservationRepository;
import com.advanceacademy.moonlighthotel.service.restaurant.SeatReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SeatReservationServiceImpl implements SeatReservationService {

    private final SeatReservationRepository seatReservationRepository;

    @Autowired
    public SeatReservationServiceImpl(SeatReservationRepository seatReservationRepository) {
        this.seatReservationRepository = seatReservationRepository;
    }

    @Override
    public SeatReservation createReservation(SeatReservation reservation) {
        return seatReservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        seatReservationRepository.deleteById(id);
    }

    @Override
    public List<SeatReservation> getReservationsByUserId(Long userId) {
        return seatReservationRepository.findByUserId(userId).orElseThrow(()-> new ResourceNotFoundException(String.format("No seat reservations found for user id %s.", userId)));
    }

    @Override
    public List<SeatReservation> getReservationsByDate(LocalDate date) {
        return seatReservationRepository.findByDate(date).orElseThrow(() -> new ResourceNotFoundException(String.format("No seat reservations found for date %s.", date)));
    }

}
