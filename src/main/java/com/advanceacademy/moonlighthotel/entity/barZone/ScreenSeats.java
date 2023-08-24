package com.advanceacademy.moonlighthotel.entity.barZone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private String position;

    @ManyToOne
    @JoinColumn(name = "seat_numbers")
    private ScreenReservation reservation;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screenId;
}