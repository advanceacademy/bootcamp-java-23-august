package com.advanceacademy.moonlighthotel.entity.barZone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Screen_Seats")
public class ScreenSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String position;

    @OneToMany(mappedBy = "screenSeatsNumbers")
    @JsonBackReference
    private List<ScreenReservation> reservations = new ArrayList<>();

    @ManyToOne
    @JsonManagedReference
    private ScreenBar screenId;
}