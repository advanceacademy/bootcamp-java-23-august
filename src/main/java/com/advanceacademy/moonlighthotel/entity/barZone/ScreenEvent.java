package com.advanceacademy.moonlighthotel.entity.barZone;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "screen_events")
public class ScreenEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "events", nullable = false)
    private String event;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date eventDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screen_id")
    private Screen screenBar;
}
