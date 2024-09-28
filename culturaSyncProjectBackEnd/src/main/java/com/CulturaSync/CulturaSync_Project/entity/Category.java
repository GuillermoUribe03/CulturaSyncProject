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
@Table(name = "categories")
public class Category {

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

    @ManyToMany
    @JoinTable(
            name = "category_event_types",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "event_type_id")
    )
    private Set<EventType> eventTypes = new HashSet<>();

    @ManyToMany(mappedBy = "preferredCategories")
    private Set<User> users = new HashSet<>();

}
