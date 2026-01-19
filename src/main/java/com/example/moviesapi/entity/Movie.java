package com.example.moviesapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @NotBlank(message = "Movie title must not be blank")
    private String title;

    @NotNull(message = "Release year is required")
    @Min(value = 1888, message = "Release year must be valid")
    private Integer releaseYear;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be greater than 0")
    private Integer duration;  // in minutes

    @ManyToMany
    @JoinTable(
        name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "movie_actor",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    // --- Getters ---
    public Long getId() { return id; }  // immutable
    public String getTitle() { return title; }
    public Integer getReleaseYear() { return releaseYear; }
    public Integer getDuration() { return duration; }
    public Set<Genre> getGenres() { return genres; }
    public Set<Actor> getActors() { return actors; }

    // --- Setters ---
    public void setTitle(String title) { this.title = title; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }
    public void setDuration(Integer duration) { this.duration = duration; }
}
