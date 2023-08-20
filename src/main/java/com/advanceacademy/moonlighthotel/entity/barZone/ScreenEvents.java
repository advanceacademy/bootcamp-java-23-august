package com.advanceacademy.moonlighthotel.entity.barZone;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Screen_Events")
public class ScreenEvents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String event;

    private Date eventDate;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "screen_bar_id")
    private ScreenBar screenBar;
}
