package com.advanceacademy.moonlighthotel.entity.barZone;

import lombok.Getter;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BarZone barZone;


    public Screen(Long screenId) {
    }
}