package com.advanceacademy.moonlighthotel.controller;

import com.advanceacademy.moonlighthotel.entity.car.FileResource;
import com.advanceacademy.moonlighthotel.service.car.FileResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping(value = "/api/v1/resources")
public class FileResourceController {

    private final FileResourceService fileResourceService;

    public FileResourceController(FileResourceService fileResourceService) {
        this.fileResourceService = fileResourceService;
    }

    @GetMapping(value = "/images/{carId}")
    @Operation(
            description = "Get images for a car",
            summary = "Retrieve Images for a Car",
            responses = {
                    @ApiResponse(
                            description = "Images retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FileResource.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - No images found for the specified car",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "carId",
                            description = "The ID of the car to retrieve images for",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "findAllResources",
            tags = {"Image Resources"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
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
    @Operation(
            description = "Get an image resource",
            summary = "Retrieve Image Resource",
            responses = {
                    @ApiResponse(
                            description = "Image retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "image/jpeg"
                            )
                    ),
                    @ApiResponse(
                            description = "Image retrieved successfully",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "image/png"
                            )
                    ),
                    @ApiResponse(
                            description = "Not Found - Image not found for the specified resource",
                            responseCode = "404"
                    )
            },
            parameters = {
                    @Parameter(
                            name = "fileResourceId",
                            description = "The ID of the image resource to retrieve",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "integer", format = "int64")
                    )
            },
            operationId = "findResource",
            tags = {"Image Resources"},
            security = @SecurityRequirement(name = "Bearer Token")
    )
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
