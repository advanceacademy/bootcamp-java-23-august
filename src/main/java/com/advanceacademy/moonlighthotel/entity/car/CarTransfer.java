package com.advanceacademy.moonlighthotel.entity.car;

import com.advanceacademy.moonlighthotel.entity.PaymentStatus;
import com.advanceacademy.moonlighthotel.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user ;

    @ManyToOne
    @JoinColumn(name = "car_id",nullable = false)
    private Car car;

    @Column(name = "payment_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus ;

}
