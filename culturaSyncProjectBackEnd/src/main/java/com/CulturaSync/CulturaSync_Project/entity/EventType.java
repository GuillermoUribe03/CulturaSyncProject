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
@Table(name = "event_types")
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(length = 255)
    @Size(max = 255)
    private String description;

    @ManyToMany(mappedBy = "eventTypes")
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "preferredEventTypes")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "eventType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events = new HashSet<>();

}
