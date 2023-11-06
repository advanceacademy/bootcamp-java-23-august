package com.advanceacademy.moonlighthotel.service.barZone.impl;

import com.advanceacademy.moonlighthotel.entity.barZone.ScreenReservation;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenReservationRepository;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenReservationServiceImpl implements ScreenReservationService {

    private final ScreenReservationRepository screenReservationRepository;

    @Autowired
    public ScreenReservationServiceImpl(ScreenReservationRepository screenReservationRepository) {
        this.screenReservationRepository = screenReservationRepository;
    }

    @Override
    public ScreenReservation createScreenReservation(ScreenReservation request) {
//        boolean isMatch = screenReservationRepository.findAll()
//                .stream()
//                .anyMatch(screenReservation -> screenReservation
//                        .getScreenEvent()
//                        .getScreen()
//                        .equals(request.getScreenEvent().getScreen()));
//        if (isMatch){
//
//        }
        return screenReservationRepository.save(request);
    }

    @Override
    public ScreenReservation getScreenReservationById(Long id) {
        Optional<ScreenReservation> foundScreenReservation = screenReservationRepository.findById(id);
        if (foundScreenReservation.isPresent()) {
            return foundScreenReservation.get();
        } else
            throw new ResourceNotFoundException(String.format("Screen reservation with id %d not found", id));
    }

    @Override
    public List<ScreenReservation> getAllScreenReservations() {
        return screenReservationRepository.findAll();
    }

    @Override
    public ScreenReservation updateScreenReservation(Long id, ScreenReservation screenReservation) {
        return null;
    }

    @Override
    public void deleteScreenReservationById(Long id) {
        Optional<ScreenReservation> foundScreenReservation = screenReservationRepository.findById(id);
        if (foundScreenReservation.isPresent()) {
            screenReservationRepository.deleteById(id);
        } else
            throw new ResourceNotFoundException(String.format("Screen reservation with id %d not found", id));
    }

    @Override
    public List<ScreenReservation> getScreenReservationByUserId(User user) {
        return screenReservationRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("You don't have screen reservation"));
    }
}
