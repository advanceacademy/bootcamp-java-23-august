package com.advanceacademy.moonlighthotel.entities.car;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name ="car_categories")
public class CarCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id ;

    @NotNull
    @Column(name = "car_types",nullable = false)
    @Enumerated(EnumType.STRING)
    private CarType carTypes ;

    @NotNull
    @Column(name = "seats",nullable = false)
    private Integer seats ;

    @Column(name = "price_per_days",nullable = false)
    private Double pricePerDay ;



}

