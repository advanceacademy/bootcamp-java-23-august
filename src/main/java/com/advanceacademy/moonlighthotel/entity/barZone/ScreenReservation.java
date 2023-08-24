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

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "screen_event")
    private String screenEvent;

    @Column(name = "total_price")
    private long totalPrice;


    @OneToMany(mappedBy = "reservation")
    public Set<ScreenSeats> screenSeats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_payed")
    private boolean isPayed;

}
