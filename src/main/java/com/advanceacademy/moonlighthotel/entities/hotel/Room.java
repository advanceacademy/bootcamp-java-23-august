package com.advanceacademy.moonlighthotel.entities.hotel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "room_number",unique = true)
    private Integer roomNumber;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "room_view")
    @Enumerated(EnumType.STRING)
    private RoomView roomView;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<RoomReservation> reservation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "room_facility",
            joinColumns = {@JoinColumn(name = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "facility_id")}
    )
    private Set<RoomFacility> roomFacilities;


}
