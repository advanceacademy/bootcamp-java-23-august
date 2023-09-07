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
public class CarBaseRequestDto {
    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotBlank
    private String year;

    @NotBlank
    private String carCategoryId;

}
