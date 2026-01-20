package com.example.moviesapi.controller;

import com.example.moviesapi.entity.Actor;
import com.example.moviesapi.entity.Movie;
import com.example.moviesapi.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Create a new movie
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@Valid @RequestBody Movie movie) {
        return movieService.create(movie);
    }

    // Get all movies with optional filtering
    @GetMapping
    public Page<Movie> getAll(
            Pageable pageable,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long actor) {

        if (genre != null || year != null || actor != null) {
            List<Movie> filtered = movieService.filter(genre, year, actor);
            return new PageImpl<>(filtered);
        }

        return movieService.getAll(pageable);
    }

    // Get movie by ID
    @GetMapping("/{id}")
    public Movie getById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    // Partial update (PATCH)
    @PatchMapping("/{id}")
    public Movie update(@PathVariable Long id, @RequestBody Movie updates) {
        return movieService.update(id, updates);
    }

    // Delete movie
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean force) {
        movieService.deleteMovie(id, force);
    }

    // Get all actors starring in a movie
    @GetMapping("/{id}/actors")
    public List<Actor> getActors(@PathVariable Long id) {
        return movieService.getActors(id);
    }

    // Search movies by title (case-insensitive, partial match)
    @GetMapping("/search")
    public List<Movie> search(@RequestParam String title) {
        return movieService.search(title);
    }
}
