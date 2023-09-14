package com.advanceacademy.moonlighthotel.entity.restaurant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurant_bar_seats")
public class SeatRestaurant {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "zone", nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantZone zone;

}
