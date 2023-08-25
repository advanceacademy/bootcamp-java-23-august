package com.advanceacademy.moonlighthotel.entity.barZone;

import com.advanceacademy.moonlighthotel.entities.user.User;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;

    @NotNull
    @Column(name = "screen_event", nullable = false)
    private ScreenEvent screenEvent;

    @NotNull
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToMany
    @Column(name = "seats", nullable = false)
    public Set<ScreenSeats> screenSeats = new HashSet<>(21);

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


}
