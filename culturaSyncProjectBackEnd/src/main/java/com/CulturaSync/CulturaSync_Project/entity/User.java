package com.CulturaSync.CulturaSync_Project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(max = 30)
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(max = 30)
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotNull
    @Email
    private String email;

    @Column(nullable = false)
    @NotNull
    private LocalDate registrationDate;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_preferences_categories",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> preferredCategories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_preferences_event_types",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_type_id")
    )
    private Set<EventType> preferredEventTypes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Notification> notifications = new HashSet<>();

}
