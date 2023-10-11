package com.advanceacademy.moonlighthotel.converter.car;

import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import org.springframework.stereotype.Component;

@Component
public class CarTransferConverter {

    public CarTransfer toCarTransfer(CarTransferRequestDto carTransferRequestDto){

        return CarTransfer.builder()
                .car(carTransferRequestDto.getCar())
                .date(carTransferRequestDto.getDate())
                .build();
    }

    public CarTransferResponseDto toResponse(CarTransfer savedCarTransfer){

        return new CarTransferResponseDto(savedCarTransfer.getId(), savedCarTransfer.getDate(), savedCarTransfer.getPrice());
    }
}
