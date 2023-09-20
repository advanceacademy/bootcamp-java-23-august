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

    @OneToMany(mappedBy = "car", cascade = CascadeType.MERGE, fetch = FetchType.LAZY) //still displayed as EAGER !?
    @JsonBackReference
    private List<FileResource> fileResources = new ArrayList<>();

}
