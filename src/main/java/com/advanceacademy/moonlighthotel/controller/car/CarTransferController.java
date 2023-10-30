package com.advanceacademy.moonlighthotel.controller.car;

import com.advanceacademy.moonlighthotel.converter.car.CarTransferConverter;
import com.advanceacademy.moonlighthotel.dto.car.CarBaseResponseDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.service.car.CarTransferService;
import com.advanceacademy.moonlighthotel.service.car.impl.CarTransferServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1")
public class CarTransferController {

    private final CarTransferService carTransferService;

    private final CarTransferConverter carTransferConvertor;

    public CarTransferController(CarTransferService carTransferService, CarTransferConverter carTransferConvertor) {
        this.carTransferService = carTransferService;
        this.carTransferConvertor = carTransferConvertor;
    }

    @PostMapping(path = "/user/cars-reservation/register-transfer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CarTransferResponseDto> registerTransfer(@RequestBody CarTransferRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carTransferService.bookCarTransfer(request));
    }

    @GetMapping("/admin/car-reservation/get-all")
    public ResponseEntity<List<CarTransferResponseDto>> getAllCarReservations() {
        List<CarTransfer> allCarReservations = carTransferService.getAllCarTransfer();

        if (allCarReservations.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        List<CarTransferResponseDto> carTransferResponse = allCarReservations.stream().map(carTransferConvertor::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok(carTransferResponse) ;

    }

}

