package com.advanceacademy.moonlighthotel.entity.restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_RESERVATIONS")
public class TableReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "HOUR", nullable = false)
    private LocalDateTime hour;

    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "TABLE_NUMBER")
    private TableRestaurant table;


    @Column(name = "PAYMENT_STATUS")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "COUNT_PEOPLE")
    private int countPeople;

}
