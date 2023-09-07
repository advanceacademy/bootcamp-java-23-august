package com.advanceacademy.moonlighthotel.service.car.impl;

import com.advanceacademy.moonlighthotel.dto.car.FileResourceResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.FileResourceRepository;
import com.advanceacademy.moonlighthotel.service.car.FileResourceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileResourceServiceImpl implements FileResourceService {
    private final FileResourceRepository fileResourceRepository;

    private final CarRepository carRepository;

    @Override
    @Transactional
    public FileResourceResponseDto uploadImages(MultipartFile[] files, Long carId) throws IOException, SQLIntegrityConstraintViolationException {
        if (carRepository.findById(carId).isEmpty()) {
            throw new EntityNotFoundException("Car with ID " + carId + " isn't found!");
        }
        if (carRepository.getReferenceById(carId).getFileResources().size() >= 3) {
            throw new SQLIntegrityConstraintViolationException("Images of the car have reached max count of 3 files.");
        }
        FileResource newImage = new FileResource();
        for (MultipartFile image : files) {
            newImage.setValue(image.getBytes());
            newImage.setImageName(String.valueOf(UUID.randomUUID()));
            newImage.setCar(carRepository.findById(carId).orElseThrow(
                    () -> new EntityNotFoundException(("Car with ID " + carId + " isn't found!"))));
            fileResourceRepository.save(newImage);
        }
        if (fileResourceRepository.findByImageName(newImage.getImageName()).isPresent()) {
            newImage = fileResourceRepository.findByImageName(newImage.getImageName()).get();
        }
        return FileResourceResponseDto.builder()
                .id(newImage.getId())
                .fileName(newImage.getImageName())
                .carId(newImage.getCar().getId())
                .build();
    }
    public Optional<List<FileResource>> findByCarId(Long carId) {
        return fileResourceRepository.findByCarId(carId);
    }
}

