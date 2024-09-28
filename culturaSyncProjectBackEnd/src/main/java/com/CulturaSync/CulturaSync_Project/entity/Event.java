package com.CulturaSync.CulturaSync_Project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 255)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(max = 500)
    private String description;

    @Column(nullable = false)
    @NotNull
    @FutureOrPresent
    private LocalDate eventDate;

    @Column(nullable = false)
    @NotNull
    private LocalTime startTime;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Integer capacity;

    @Column(length = 255)
    @Size(max = 255)
    private String eventLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType eventType;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventActivity> eventActivities = new HashSet<>();

    // Metodo para verificar si un evento tiene actividades
    public boolean hasActivities(){
        return !eventActivities.isEmpty();
    }
}
