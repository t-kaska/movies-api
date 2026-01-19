package com.example.moviesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @NotBlank(message = "Actor name must not be blank")
    private String name;

    @NotNull(message = "Birth date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;  // ISO 8601 format (YYYY-MM-DD)

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    // --- Getters ---
    public Long getId() { return id; }  // immutable
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public Set<Movie> getMovies() { return movies; }

    // --- Setters ---
    public void setName(String name) { this.name = name; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
