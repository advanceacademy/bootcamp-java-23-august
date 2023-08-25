package com.advanceacademy.moonlighthotel.entity.barZone;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "screen_seats")
public class ScreenSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "position", nullable = false)
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screenId;
}