package com.advanceacademy.moonlighthotel.dto.car;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarTransferResponseDto {
    private String id;
    private String make;
    private String model;
    private String seats;
    private String date;
    private String price;
}
