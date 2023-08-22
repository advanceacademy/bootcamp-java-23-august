package com.advanceacademy.moonlighthotel.entities.car;

import jakarta.persistence.*;
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

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "value")
    @Lob
    private byte[] value;

    @ManyToMany(mappedBy = "car",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Car car ;



}

