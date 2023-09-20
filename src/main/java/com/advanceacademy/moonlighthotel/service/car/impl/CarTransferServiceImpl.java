package com.advanceacademy.moonlighthotel.service.car.impl;

import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.repository.car.CarTransferRepository;
import com.advanceacademy.moonlighthotel.service.car.CarTransferService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarTransferServiceImpl implements CarTransferService {
    private final CarTransferRepository carTransferRepository;

    public CarTransferServiceImpl(CarTransferRepository carTransferRepository) {
        this.carTransferRepository = carTransferRepository;
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
}
