package com.advanceacademy.moonlighthotel.service.barZone.impl;

import com.advanceacademy.moonlighthotel.entity.barZone.Screen;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.barZoneRepository.ScreenRepository;
import com.advanceacademy.moonlighthotel.service.barZone.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenServiceImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public Screen getScreenById(Long id) {
        Optional<Screen> foundScreen = screenRepository.findById(id);
        if (foundScreen.isPresent()) {
            return foundScreen.get();
        } else {
            throw new ResourceNotFoundException(String.format("Screen with id %d not found", id));
        }
    }

    @Override
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    public Screen updateScreen(Long id, Screen request) {
        Optional<Screen> foundScreen = screenRepository.findById(id);
        if (foundScreen.isPresent()){
            Screen updateScreen = foundScreen.get();
            if (request.getBarZone() != null){
                updateScreen.setBarZone(request.getBarZone());
                return screenRepository.save(updateScreen);
            }else throw new IllegalArgumentException("barZone cannot be null");
        }else {
            throw new ResourceNotFoundException(String.format("Screen with id %d not found", id));
        }
    }

    @Override
    public void deleteScreenById(Long id) {
        Optional<Screen> foundScreen = screenRepository.findById(id);
        if (foundScreen.isPresent()){
            screenRepository.deleteById(id);
        }else throw new ResourceNotFoundException(String.format("Screen with id %d not found", id));
    }
}
