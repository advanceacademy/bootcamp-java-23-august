package com.advanceacademy.moonlighthotel.service.barZone.impl;

import com.advanceacademy.moonlighthotel.entity.barZone.ScreenSeat;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenSeatRepository;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenSeatServiceImpl implements ScreenSeatService {

    private final ScreenSeatRepository screenSeatRepository;

    @Autowired
    public ScreenSeatServiceImpl(ScreenSeatRepository screenSeatRepository) {
        this.screenSeatRepository = screenSeatRepository;
    }

    @Override
    public ScreenSeat createScreenSeat(ScreenSeat screenSeat) {
        List<ScreenSeat> allSeats = screenSeatRepository.findAll();
        boolean isScreenMatch = allSeats.stream()
                .anyMatch(seat -> seat.getScreenId().getBarZone().equals(screenSeat.getScreenId().getBarZone()));
        if (isScreenMatch){
            for (ScreenSeat seat : allSeats) {
                if (seat.getPosition().equals(screenSeat.getPosition())){
                    throw new DuplicateRecordException(String.format("Seat with position %d already exists", screenSeat.getPosition()));
                }
            }return screenSeatRepository.save(screenSeat);
        }else
            throw new IllegalArgumentException("No Screen with the specified barZone found");
    }

    @Override
    public ScreenSeat getScreenSeatById(Long id) {
        Optional<ScreenSeat> foundScreenSeat = screenSeatRepository.findById(id);
        if (foundScreenSeat.isPresent()){
            return foundScreenSeat.get();
        }else
            throw new ResourceNotFoundException(String.format("Screen seat with id %d not found", id));
    }

    @Override
    public List<ScreenSeat> getAllScreenSeats() {
        return screenSeatRepository.findAll();
    }

    @Override
    public ScreenSeat updateScreenSeat(Long id, ScreenSeat request) {
        Optional<ScreenSeat> foundScreenSeat = screenSeatRepository.findById(id);
        if (foundScreenSeat.isPresent()){
            ScreenSeat updateScreenSeat = foundScreenSeat.get();
            if (request.getPosition() != null){
                updateScreenSeat.setPosition(request.getPosition());
            }else
                throw new IllegalArgumentException("Screen seat position cannot be null");

            if (request.getScreenId() != null){
                updateScreenSeat.setScreenId(request.getScreenId());
            }
            return screenSeatRepository.save(updateScreenSeat);
        }else
            throw new ResourceNotFoundException(String.format("Screen seat with id %d not found", id));
    }

    @Override
    public void deleteScreenSeatById(Long id) {
        Optional<ScreenSeat> foundScreenSeat = screenSeatRepository.findById(id);
        if (foundScreenSeat.isPresent()){
            screenSeatRepository.deleteById(id);
        }else
            throw new ResourceNotFoundException(String.format("Screen seat with id %d not found", id));
    }
}
