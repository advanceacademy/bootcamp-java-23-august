package com.advanceacademy.moonlighthotel.entity.barZone;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Bar_Screen")

public class ScreenBar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private BarZone barZone;

    @OneToMany(mappedBy = "screenId", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JsonBackReference
    public Set<ScreenSeats> screenSeats;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "screen_id")
    private ScreenEvents screenEvents;
}
