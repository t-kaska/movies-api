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

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@Valid @RequestBody Movie movie) {
        return movieService.create(movie);
    }

    // GET ALL / FILTERED / PAGINATED
    @GetMapping
    public Page<Movie> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long actor
    ) {
        if (page < 0 || size <= 0 || size > 100) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page must be >= 0, size must be 1-100"
            );
        }

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").ascending()
        );

        return movieService.getAll(genre, year, actor, pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Movie getById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    // PATCH
    @PatchMapping("/{id}")
    public Movie update(
            @PathVariable Long id,
            @RequestBody Movie updates
    ) {
        return movieService.update(id, updates);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean force
    ) {
        movieService.deleteMovie(id, force);
    }

    // ACTORS IN MOVIE
    @GetMapping("/{id}/actors")
    public List<Actor> getActors(@PathVariable Long id) {
        return movieService.getActors(id);
    }

    // SEARCH (PAGINATED)
    @GetMapping("/search")
    public Page<Movie> search(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("id").ascending()
        );

        return movieService.search(title, pageable);
    }
}
