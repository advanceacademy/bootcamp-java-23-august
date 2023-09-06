package com.advanceacademy.moonlighthotel.dto.car;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileResourceResponseDto {
    private Long id;
    private String fileName;
    private Long carId;
}
