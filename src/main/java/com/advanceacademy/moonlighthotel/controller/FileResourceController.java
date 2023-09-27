package com.advanceacademy.moonlighthotel.controller;

import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import com.advanceacademy.moonlighthotel.service.car.FileResourceService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/resources")
public class FileResourceController {

    private final FileResourceService fileResourceService;

    public FileResourceController(FileResourceService fileResourceService) {
        this.fileResourceService = fileResourceService;
    }

    @GetMapping(value = "/images/{carId}")
    public ResponseEntity<List<FileResource>> findAllResources(@PathVariable Long carId) {
        return ResponseEntity.ok(fileResourceService.findByCarId(carId).orElseThrow());
    }


//    @GetMapping(value = "/images/{carId}")
//    public ResponseEntity<List<ResponseEntity<byte[]>>> findAllResourceImages(@PathVariable Long carId) {
//        List<FileResource> fileResources = fileResourceService.findByCarId(carId).orElseThrow();
//
//        List<ResponseEntity<byte[]>> imageResponses = new ArrayList<>();
//
//        for (FileResource fileResource : fileResources) {
//            byte[] imageData = fileResource.getValue();
//            String imageName = fileResource.getImageName();
//            String contentType = determineContentType(imageName);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(contentType));
//
//            ResponseEntity<byte[]> imageResponse = new ResponseEntity<>(imageData, headers, HttpStatus.OK);
//            imageResponses.add(imageResponse);
//        }
//
//        return ResponseEntity.ok(imageResponses);
//    }

    private String determineContentType(String imageName) {
        if (imageName != null && imageName.toLowerCase().endsWith(".png")) {
            return MediaType.IMAGE_PNG_VALUE;
        } else {
            return MediaType.IMAGE_JPEG_VALUE; // Default to JPEG
        }
    }

    @GetMapping(value = "/{fileResourceId}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> findResource(@PathVariable Long fileResourceId) {
        FileResource fileResource = fileResourceService.findById(fileResourceId);
        byte[] imageData = fileResource.getValue();

        // Determine the content type based on the image name or other criteria.
        // In this example, we're assuming the image name contains the file extension.
        String imageName = fileResource.getImageName();
        String contentType = MediaType.IMAGE_JPEG_VALUE; // Default to JPEG
        if (imageName != null && imageName.toLowerCase().endsWith(".png")) {
            contentType = MediaType.IMAGE_PNG_VALUE;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
