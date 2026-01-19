package com.example.moviesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre name must not be blank")
    @Size(min = 2, max = 50, message = "Genre name must be between 2 and 50 characters")
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new HashSet<>();

    // --- Getters ---
    public Long getId() { return id; }  // ID is immutable
    public String getName() { return name; }
    public Set<Movie> getMovies() { return movies; }

    // --- Setters ---
    public void setName(String name) { this.name = name; }
}
