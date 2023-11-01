package com.advanceacademy.moonlighthotel.entity.restaurant;

import com.advanceacademy.moonlighthotel.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "table_reservations",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_reservation",
                columnNames = {"date", "hour", "table_restaurant_number"}
        )
)
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "hour", nullable = false)
    private LocalTime hour;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_Smoking", nullable = false)
    private boolean isSmoking;

    @Column(name = "table_zone", nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantZone zone;

    @ManyToOne
    @JoinColumn(name = "table_restaurant_number")
    private TableRestaurant table;

    @Column(name = "count_people")
    private Integer countPeople;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

}
