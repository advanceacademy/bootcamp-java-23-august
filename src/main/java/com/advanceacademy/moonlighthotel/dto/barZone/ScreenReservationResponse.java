package com.advanceacademy.moonlighthotel.dto.barZone;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenEvent;
import com.advanceacademy.moonlighthotel.entity.barZone.ScreenSeat;
import com.advanceacademy.moonlighthotel.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ScreenReservationResponse {

    private Long id;
    private Date reservationDate;
    private ScreenEvent screenEvent;
    private Double totalPrice;
    private PaymentStatus paymentStatus;
    public Set<ScreenSeat> screenSeats;
    private User user;
}
