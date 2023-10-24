package com.advanceacademy.moonlighthotel.dto.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.advanceacademy.moonlighthotel.entity.user.User;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarTransferResponseDto {
    private Long id;
    private Car carId;
    private User user;
    private LocalDate date;
    private Double price;


}