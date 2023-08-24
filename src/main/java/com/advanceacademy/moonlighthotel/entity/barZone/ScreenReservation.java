package com.advanceacademy.moonlighthotel.entity.barZone;

import com.advanceacademy.moonlighthotel.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;

    @NotNull
    @Column(name = "screen_event", nullable = false)
    private String screenEvent;

    @NotNull
    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @NotNull
    @Column(name = "is_payed", nullable = false)
    private boolean isPayed;

    @OneToMany(mappedBy = "reservation")
    public Set<ScreenSeats> screenSeats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


}
