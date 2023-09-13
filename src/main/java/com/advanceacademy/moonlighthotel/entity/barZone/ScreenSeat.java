package com.advanceacademy.moonlighthotel.entity.barZone;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "screen_seats")
public class ScreenSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "position", nullable = false)
    @Min(1)
    @Max(21)
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screenId;
}