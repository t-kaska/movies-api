package com.example.moviesapi.controller;

import com.example.moviesapi.entity.Actor;
import com.example.moviesapi.entity.Movie;
import com.example.moviesapi.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    // Create a new actor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor create(@Valid @RequestBody Actor actor) {
        return actorService.create(actor);
    }

    // Get all actors, optionally filtered by name
    @GetMapping
    public List<Actor> getAll(@RequestParam(required = false) String name) {
        return actorService.getAll(name);
    }

    // Get actor by ID
    @GetMapping("/{id}")
    public Actor getById(@PathVariable Long id) {
        return actorService.getById(id);
    }

    // Partial update (PATCH)
    @PatchMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor updates) {
        return actorService.update(id, updates);
    }

    // Delete actor (force optional)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id,
                       @RequestParam(defaultValue = "false") boolean force) {
        actorService.delete(id, force);
    }

    // Get all movies an actor has appeared in
    @GetMapping("/{id}/movies")
    public List<Movie> getMovies(@PathVariable Long id) {
        return actorService.getMovies(id);
    }
}
