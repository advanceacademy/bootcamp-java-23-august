package com.advanceacademy.moonlighthotel.entity.barZone;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "screen_reservations")
public class ScreenReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date reservationDate;

    private String screenEvent;


    private long totalPrice;


    @OneToMany(mappedBy = "reservation")
    public Set<ScreenSeats> screenSeats;

    //private User user;

    private boolean isPayed;

}
