package com.advanceacademy.moonlighthotel.entity.barZone;

import com.advanceacademy.moonlighthotel.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "screen_reservations")
public class ScreenReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date reservationDate;

    private String screenEvent;


    private long totalPrice;


    @OneToMany(mappedBy = "reservation")
    public Set<ScreenSeats> screenSeats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isPayed;

}
