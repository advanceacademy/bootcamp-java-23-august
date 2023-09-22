package com.advanceacademy.moonlighthotel.repository.car;

import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileResourceRepository extends JpaRepository<FileResource, Long> {

    Optional<List<FileResource>> findByCarId(Long carId);
    Optional<FileResource> findByImageName(String imageName);

    Optional<FileResource> findByValue(byte[] imageValue);
}
