package com.advanceacademy.moonlighthotel.entity.hotel;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "room_reservations")
public class RoomReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "adult")
    private Integer adult;

    @Column(name = "children")
    private Integer children;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne()
    @JoinColumn(name = "room_id")
    @JsonManagedReference
    private Room room;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
