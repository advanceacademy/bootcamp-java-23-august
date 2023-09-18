package com.advanceacademy.moonlighthotel.entity.restaurant;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurant_tables")
public class TableRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @Column(name = "zone", nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantZone zone;

    @Column(name = "is_smoking", nullable = false)
    private Boolean isSmoking;

    @Column(name = "seats", nullable = false)
    private Integer seats;
}
