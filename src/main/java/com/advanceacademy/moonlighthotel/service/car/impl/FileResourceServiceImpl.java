package com.advanceacademy.moonlighthotel.service.car.impl;

import com.advanceacademy.moonlighthotel.dto.car.FileResourceResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.repository.car.FileResourceRepository;
import com.advanceacademy.moonlighthotel.service.car.FileResourceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
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
            throw new ResourceNotFoundException(String.format("Car with id %d not found", carId));
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

    @Override
    public List<FileResource> findByName(String name) {
        List<FileResource> allImages = fileResourceRepository.findAll();
        List<FileResource> foundImages = new ArrayList<>();
        for (FileResource image : allImages) {
            if (image.getImageName().toLowerCase().startsWith(name)) {
                foundImages.add(image);
            }
        }
        return foundImages;

    }

    @Override
    public FileResource updateFileResource(Long id, FileResource fileResource) {
        Optional<FileResource> foundFileResource = fileResourceRepository.findById(id);
        FileResource updateFileResource = new FileResource();
        if (foundFileResource.isPresent()) {
            updateFileResource = foundFileResource.get();
            updateFileResource.setCar(fileResource.getCar());
            updateFileResource.setValue(fileResource.getValue());
            updateFileResource.setImageName(fileResource.getImageName());

            fileResourceRepository.save(updateFileResource);
        }
        return updateFileResource;
    }

    @Override
    public List<FileResource> getAllResource() {
        return fileResourceRepository.findAll();
    }

    public Optional<List<FileResource>> findByCarId(Long carId) {
        return fileResourceRepository.findByCarId(carId);
    }

    @Override
    public FileResource findById(Long fileResourceId) {
        return fileResourceRepository.findById(fileResourceId).orElseThrow();
    }

    public byte[] readImageFromFileOrSource(String carCategory, String imageName) throws IOException {
        String carCategoryImagesFolder = "static\\" + carCategory + "\\";

        String carCategoryImagePath = carCategoryImagesFolder + imageName;

        try {
            ClassPathResource classPathResource = new ClassPathResource(carCategoryImagePath);

            if (classPathResource.exists() && !classPathResource.getFile().isDirectory()) {
                InputStream inputStream = classPathResource.getInputStream();
                return StreamUtils.copyToByteArray(inputStream);
            } else {
                throw new ResourceNotFoundException("Image file not found:" + carCategoryImagePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

