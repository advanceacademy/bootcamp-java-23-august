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
<<<<<<<< HEAD:src/main/java/com/advanceacademy/moonlighthotel/entity/barZone/Screen.java
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
========
@Entity(name = "screen_events")
public class ScreenEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Column(name = "events", nullable = false)
    private String event;

    @Column(name = "date", nullable = false)
    private Date eventDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screen_id")
    private Screen screenBar;
>>>>>>>> origin/JB2AT1-3-Create-Screen-Bar-and-all-related-entities:src/main/java/com/advanceacademy/moonlighthotel/entity/barZone/ScreenEvents.java
}
