package com.advanceacademy.moonlighthotel.service.car.impl;

import com.advanceacademy.moonlighthotel.converter.car.CarTransferConverter;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarTransferRepository;
import com.advanceacademy.moonlighthotel.service.car.CarTransferService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarTransferServiceImpl implements CarTransferService {
    private final CarTransferRepository carTransferRepository;

    private final CarTransferConverter carTransferConverter;

    public CarTransferServiceImpl(CarTransferRepository carTransferRepository, CarTransferConverter carTransferConverter) {
        this.carTransferRepository = carTransferRepository;
        this.carTransferConverter = carTransferConverter;
    }

    @Override
    public CarTransfer addCarTransfer(CarTransfer carTransfer) {
        Optional<CarTransfer> savedTransfer = carTransferRepository.findById(carTransfer.getId());
        if (savedTransfer.isPresent()) {
            throw new EntityNotFoundException("Car transfer with Id# " + carTransfer.getId() + " has already existed");
        }
        return carTransferRepository.save(carTransfer);
    }

    @Override
    public CarTransferResponseDto bookCarTransfer(CarTransferRequestDto request) {

        CarTransfer carTransfer = carTransferConverter.toCarTransfer(request);
        CarTransfer savedCarTransfer = carTransferRepository.save(carTransfer);
        return carTransferConverter.toResponse(savedCarTransfer);
    }

    @Override
    public Optional<CarTransfer> getTransferByID(Long id) {
        return carTransferRepository.findById(id);
    }

    @Override
    public List<CarTransfer> getAllCarTransfer() {
        return carTransferRepository.findAll();
    }


    @Override
    public CarTransfer updateCarTransfer(CarTransfer updatedCarTransfer) {
        return carTransferRepository.save(updatedCarTransfer);
    }

    @Override
    public void deleteTransferById(Long id) {
        carTransferRepository.deleteById(id);
    }

    @Override
    public List<CarTransfer> getCarTransfersByUser(User user) {
        return carTransferRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("You have no car transfer"));
    }
}
