package com.advanceacademy.moonlighthotel.entity.car;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id ;

    @NotNull
    @Column(name = "model",nullable = false)
    private String model ;

    @NotNull
    @Column(name = "year",nullable = false)
    private Integer year ;

    @NotNull
    @Column(name = "make",nullable = false)
    private String make ;

    @ManyToOne
    @JoinColumn(name = "car_category_id",nullable = false)
    private CarCategory carCategory;

}
