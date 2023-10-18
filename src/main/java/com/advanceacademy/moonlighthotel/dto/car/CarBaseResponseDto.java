package com.advanceacademy.moonlighthotel.dto.car;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarBaseResponseDto {

    private Long id;
    private String model;
    private Integer year;
    private String make;
    private String plateNumber;
    private Long carCategoryId;
    private String carType;
    private Integer seats;
    private Double pricePerDay;

}
