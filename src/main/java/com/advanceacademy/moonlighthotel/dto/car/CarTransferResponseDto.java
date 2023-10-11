package com.advanceacademy.moonlighthotel.dto.car;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarTransferResponseDto {
    private Long id;
    //private String make;
    //private String model;
    //private String seats;
    private LocalDate date;
    private Double price;


}
