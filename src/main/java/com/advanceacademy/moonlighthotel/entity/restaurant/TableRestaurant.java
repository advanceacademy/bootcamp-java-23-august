package com.advanceacademy.moonlighthotel.entity.restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RESTAURANT_TABLES")
public class TableRestaurant {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ZONE", nullable = false)
    @Enumerated(EnumType.STRING)
    private TableZone zone;

    @Column(name = "IS_SMOKING", nullable = false)
    private boolean isSmoking;

    @Column(name = "SEATS", nullable = false)
    private int seats;
}
