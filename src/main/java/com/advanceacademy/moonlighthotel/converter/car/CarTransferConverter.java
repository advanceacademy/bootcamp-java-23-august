package com.advanceacademy.moonlighthotel.converter.car;

import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.repository.car.CarRepository;
import com.advanceacademy.moonlighthotel.service.car.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CarTransferConverter {

    private final CarService carService;
    public CarTransfer toCarTransfer(CarTransferRequestDto carTransferRequestDto){
        Car foundCar = carService.getCarById(carTransferRequestDto.getCarId()).orElseThrow();

            return CarTransfer.builder()
                    .car(foundCar)
                    .date(carTransferRequestDto.getDate())
                    .price(foundCar.getCarCategory().getPricePerDay())
                    .paymentStatus(PaymentStatus.PAID)
                    .build();

    }

    public CarTransferResponseDto toResponse(CarTransfer savedCarTransfer){

        return new CarTransferResponseDto(
                savedCarTransfer.getId(),
                savedCarTransfer.getCar(),
                savedCarTransfer.getDate(),
                savedCarTransfer.getPrice());
    }
}
