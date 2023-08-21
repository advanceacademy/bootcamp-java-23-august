package com.advanceacademy.moonlighthotel.entity.restaurant;
import com.advanceacademy.moonlighthotel.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "table_reservation")
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "hour", nullable = false)
    private LocalDateTime hour;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "table_number")
    private TableRestaurant table;


    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "count_people")
    private Integer countPeople;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
