package com.advanceacademy.moonlighthotel.entity.barZone;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Screen_Reservations")
public class ScreenReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date reservationDate;

    private String screenEvent;

    private long totalPrice;

    @ManyToOne
    @JoinColumn(name = "screen_seats_numbers")
    @JsonManagedReference
    private ScreenSeats screenSeatsNumbers;

    //private User user;

    private boolean paymentStatus;

}
