package com.advanceacademy.moonlighthotel.entity.barZone;

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
@Entity(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BarZone barZone;


    @OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Set<ScreenEvent> screenEvents;

}