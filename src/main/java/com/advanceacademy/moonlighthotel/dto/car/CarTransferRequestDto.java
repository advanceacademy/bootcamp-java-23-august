package com.advanceacademy.moonlighthotel.dto.car;

import com.advanceacademy.moonlighthotel.entity.car.Car;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
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

    //private class CarTransferRequestDtoDeserializer extends StdDeserializer<CarTransferRequestDto> {
    //    public CarTransferRequestDtoDeserializer() {
    //        this(null);
    //    }
//
    //    public CarTransferRequestDtoDeserializer(Class<?> vc) {
    //        super(vc);
    //    }
//
    //    @Override
    //    public CarTransferRequestDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
    //            throws IOException {
    //        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    //        @NotBlank Car carId = node.get("carId").asLong(); // прочитаме carId като Long
    //        LocalDate date = LocalDate.parse(node.get("date").asText());
//
    //        CarTransferRequestDto dto = new CarTransferRequestDto();
    //        dto.setCarId(carId);
    //        dto.setDate(date);
    //        return dto;
    //    }
    //}
}
