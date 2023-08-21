package com.advanceacademy.moonlighthotel.entity.restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurants_table")
public class TableRestaurant {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "zone", nullable = false)
    @Enumerated(EnumType.STRING)
    private TableZone zone;

    @Column(name = "is_smoking", nullable = false)
    private boolean isSmoking;

    @Column(name = "seats", nullable = false)
    private int seats;
}
