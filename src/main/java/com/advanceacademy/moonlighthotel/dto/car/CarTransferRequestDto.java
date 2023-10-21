package com.advanceacademy.moonlighthotel.dto.car;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarTransferRequestDto {

    @NotBlank
    private Long carId;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

}