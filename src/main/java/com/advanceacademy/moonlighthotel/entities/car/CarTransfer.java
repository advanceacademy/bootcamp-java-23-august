package com.advanceacademy.moonlighthotel.entities.car;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_transfers")
public class CarTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id ;

    @NotNull
    @Column(name = "date",nullable = false)
    private LocalDate date ;

    @NotNull
    @Column(name = "price",nullable = false)
    private Double price ;

    @NotNull
    @Column(name = "user",nullable = false)
    private String user ;

    @OneToMany()
    @JoinColumn(name = "car_transfer_id")
    private List<Car> cars ;

    @Column(name = "payment_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus ;

}
