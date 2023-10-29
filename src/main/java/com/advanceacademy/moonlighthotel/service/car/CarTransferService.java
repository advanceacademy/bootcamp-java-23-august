package com.advanceacademy.moonlighthotel.service.car;

import com.advanceacademy.moonlighthotel.dto.car.CarTransferRequestDto;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;

import java.util.List;
import java.util.Optional;

public interface CarTransferService {
    //Create
    CarTransfer addCarTransfer(CarTransfer carTransfer);

    CarTransferResponseDto bookCarTransfer(CarTransferRequestDto requestDto);

    //Retrieve
  Optional<CarTransfer> getTransferByID (Long id);
    List<CarTransfer> getAllCarTransfer();

    //Update
    CarTransfer updateCarTransfer( CarTransfer updatedCarTransfer);
    //Delete
    void deleteTransferById(Long id);

}
