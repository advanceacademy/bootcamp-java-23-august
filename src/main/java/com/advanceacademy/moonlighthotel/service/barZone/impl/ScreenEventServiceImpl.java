package com.advanceacademy.moonlighthotel.service.barZone.impl;

import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.exception.DuplicateRecordException;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenEventRepository;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenEventServiceImpl implements ScreenEventService {

    private final ScreenEventRepository screenEventRepository;

    @Autowired
    public ScreenEventServiceImpl(ScreenEventRepository screenEventRepository) {
        this.screenEventRepository = screenEventRepository;
    }

    @Override
    public ScreenEvent createScreenEvent(ScreenEvent screenEvent) {
        boolean isMatch = screenEventRepository.findAll()
                .stream()
                .anyMatch(screen -> screen.getScreen().getBarZone()
                        .equals(screenEvent.getScreen().getBarZone())
                        && screen.getEventDate().equals(screenEvent.getEventDate())
                        && screen.getEvent().equals(screenEvent.getEvent()));
        if (!isMatch) {
            return screenEventRepository.save(screenEvent);
        } else
            throw new DuplicateRecordException(String.format("Screen event %s already exists", screenEvent.getEvent()));
    }

    @Override
    public ScreenEvent getScreenEventById(Long id) {
        Optional<ScreenEvent> foundScreenEvent = screenEventRepository.findById(id);
        if (foundScreenEvent.isPresent()) {
            return foundScreenEvent.get();
        } else
            throw new ResourceNotFoundException(String.format("Screen event with id %d not found", id));
    }

    @Override
    public List<ScreenEvent> getAllScreenEvents() {
        return screenEventRepository.findAll();
    }

    @Override
    public ScreenEvent updateScreenEvent(Long id, ScreenEvent request) {
        Optional<ScreenEvent> foundScreenEvent = screenEventRepository.findById(id);
        if (foundScreenEvent.isPresent()) {
            ScreenEvent updateScreenEvent = foundScreenEvent.get();
            if (request.getEvent() != null) {
                updateScreenEvent.setEvent(request.getEvent());
            } else
                throw new IllegalArgumentException("Event cannot be null");

            if (request.getScreen() != null) {
                updateScreenEvent.setScreen(request.getScreen());
            } else
                throw new IllegalArgumentException("Screen cannot be null");

            if (request.getEventDate() != null) {
                updateScreenEvent.setEventDate(request.getEventDate());
            } else
                throw new IllegalArgumentException("Event date cannot be null");
            return screenEventRepository.save(updateScreenEvent);

        } else
            throw new ResourceNotFoundException(String.format("Screen with id %d not found", id));
    }

    @Override
    public void deleteScreenEventById(Long id) {
        Optional<ScreenEvent> foundScreenEvent = screenEventRepository.findById(id);
        if (foundScreenEvent.isPresent()) {
            screenEventRepository.deleteById(id);
        } else
            throw new ResourceNotFoundException(String.format("Screen event with id %d not found", id));
    }

    @Override
    public ScreenEvent createScreenEventWithCheck(ScreenEvent screenEvent) {
        // Check if there are already 3 events for the given date
        long eventCount = screenEventRepository.countByEventDate(screenEvent.getEventDate());

        if (eventCount < 3) {
            // Check if the event already exists
            boolean exists = screenEventRepository.existsByEventAndEventDate(screenEvent.getEvent(), screenEvent.getEventDate());

            if (!exists) {
                return screenEventRepository.save(screenEvent);
            } else {
                throw new DuplicateRecordException("Screen event already exists for this date.");
            }
        } else {
            throw new DuplicateRecordException("Maximum 3 events allowed per day.");
        }
    }
}
