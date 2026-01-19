package com.example.moviesapi.controller;

import com.example.moviesapi.entity.Genre;
import com.example.moviesapi.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Create a new genre
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre create(@Valid @RequestBody Genre genre) {
        return genreService.create(genre);
    }

    // Get all genres
    @GetMapping
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    // Get genre by ID
    @GetMapping("/{id}")
    public Genre getById(@PathVariable Long id) {
        return genreService.getById(id);
    }

    // Partial update (PATCH)
    @PatchMapping("/{id}")
    public Genre update(@PathVariable Long id, @RequestBody Genre updates) {
        return genreService.update(id, updates);
    }

    // Delete genre (force optional)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id,
                       @RequestParam(defaultValue = "false") boolean force) {
        genreService.delete(id, force);
    }
}
