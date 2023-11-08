package com.advanceacademy.moonlighthotel.dto.user;

import com.advanceacademy.moonlighthotel.dto.barZone.ScreenReservationResponse;
import com.advanceacademy.moonlighthotel.dto.car.CarTransferResponseDto;
import com.advanceacademy.moonlighthotel.dto.hotel.RoomReservationResponseDTO;
import com.advanceacademy.moonlighthotel.dto.restaurant.TableReservationResponse;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenReservation;
import com.advanceacademy.moonlighthotel.entity.car.CarTransfer;
import com.advanceacademy.moonlighthotel.entity.hotel.RoomReservation;
import com.advanceacademy.moonlighthotel.entity.restaurant.TableReservation;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserReservationResponse {

    private List<TableReservation> tableReservation;
    private List<CarTransfer> carTransfer;
    private List<RoomReservation> roomReservation;
    private List<ScreenReservation> screenReservation;
}
