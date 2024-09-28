package com.CulturaSync.CulturaSync_Project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(max = 255)
    private String description;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events = new HashSet<>();

}
