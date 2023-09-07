package com.advanceacademy.moonlighthotel.dto.car;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarCategoryResponseDto {
    private String id;
    private String type;
    private String seats;
    private String pricePerDay;

}
