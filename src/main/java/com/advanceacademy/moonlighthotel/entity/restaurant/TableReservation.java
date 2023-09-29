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
@Table(name = "table_reservations")
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "hour", nullable = false)
    private LocalDateTime hour;

    @Column(name = "price")
    private Double price;

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
