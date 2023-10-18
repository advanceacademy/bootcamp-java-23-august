package com.advanceacademy.moonlighthotel.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarBaseRequestDto {

    @NotBlank(message = "Make is required")
    @Size(max = 30, message = "Make must be at most 30 characters")
    private String make;

    @NotBlank(message = "Model is required")
    @Size(max = 30, message = "Model must be at most 30 characters")
    private String model;

    @NotNull(message = "Year is required")
    @Positive(message = "Year must be a positive number")
    private Integer year;

    @NotBlank(message = "Plate number is required")
    @Size(max = 10, message = "Plate number must be at most 10 characters")
    private String plateNumber;

    @NotNull(message = "Car category ID is required")
    private Long carCategoryId;

}
