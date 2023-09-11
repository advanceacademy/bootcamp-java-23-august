package com.advanceacademy.moonlighthotel.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role", unique = true)
    private String userRole;

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private Set<UserRoleAsset> assets = new HashSet<>();

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private Set<UserRoleAsset> assetHashSet = new HashSet<>();
}
