package com.CulturaSync.CulturaSync_Project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 255)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(max = 255)
    private String address;

    @Column(length = 255)
    @Email
    private String email;

    @Column(length = 15)
    @Pattern(regexp = "\\\\+\\\\d{1,2}\\\\s?\\\\d{3}\\\\s?\\\\d{3}\\\\s?\\\\d{4}$", message = "Must be a valid cell phone number" )
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_location_id")
    private Location mainLocation;

    @OneToMany(mappedBy = "mainLocation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Location> subLocations = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> events = new HashSet<>();


}
