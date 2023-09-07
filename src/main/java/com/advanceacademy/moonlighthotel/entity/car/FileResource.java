package com.advanceacademy.moonlighthotel.entity.car;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "file_resources")
public class FileResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @NotNull
    @Column(name = "image_name")
    private String imageName;

    @NotNull
    @Column(name = "value")
    @Lob
    private byte[] value;

    @ManyToOne

    @JoinColumn(name = "car_id")
    @JsonManagedReference
    private Car car ;


}

