package com.advanceacademy.moonlighthotel.entity.barZone;

import jakarta.validation.constraints.NotNull;
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
@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BarZone barZone;


}