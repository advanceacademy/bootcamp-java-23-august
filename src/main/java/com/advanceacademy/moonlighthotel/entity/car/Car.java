package com.advanceacademy.moonlighthotel.entity.car;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode
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

    @NotNull
    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;

    @ManyToOne
    @JoinColumn(name = "car_category_id",nullable = false)
    private CarCategory carCategory;

    @OneToMany(mappedBy = "car", cascade = CascadeType.DETACH, fetch = FetchType.LAZY) //still displayed as EAGER !?
    @JsonBackReference
    private List<FileResource> fileResources = new ArrayList<>();

}
