package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.dto.car.FileResourceResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface FileResourceService {
    Optional<List<FileResource>> findByCarId(Long carId);

    FileResource findById(Long fileResourceId);

    FileResourceResponseDto uploadImages(MultipartFile[] file, Long carId) throws IOException, SQLIntegrityConstraintViolationException;

    List<FileResource> findByName(String name);

    FileResource updateFileResource(Long id, FileResource fileResource);

    List<FileResource> getAllResource();

}
