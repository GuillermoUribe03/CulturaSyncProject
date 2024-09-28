package com.CulturaSync.CulturaSync_Project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "event_activities")
public class EventActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 255)
    private String name;

    @Column(nullable = false)
    @NotNull
    private LocalTime startTime;

    @Column(nullable = false)
    @NotNull
    private Integer duration;

    @Column(length = 500)
    @Size(max = 500)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
