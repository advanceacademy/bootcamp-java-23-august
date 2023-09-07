package com.advanceacademy.moonlighthotel.dto.car;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarCategoryRequestDto {
    @NotBlank
    private String carType;
    @NotBlank
    private String seats;
    @NotBlank
    private String pricePerDay;
}
