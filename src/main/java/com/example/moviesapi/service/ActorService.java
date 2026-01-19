package com.example.moviesapi.service;

import com.example.moviesapi.entity.Actor;
import com.example.moviesapi.entity.Movie;
import com.example.moviesapi.exception.ResourceNotFoundException;
import com.example.moviesapi.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    // Create a new actor
    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    // Get all actors, optionally filter by name (case-insensitive)
    public List<Actor> getAll(String name) {
        if (name != null && !name.isBlank()) {
            return actorRepository.findByNameIgnoreCaseContaining(name);
        }
        return actorRepository.findAll();
    }

    // Get actor by ID
    public Actor getById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
    }

    // Update actor (PATCH)
    public Actor update(Long id, Actor updates) {
        Actor actor = getById(id);
        if (updates.getName() != null) {
            actor.setName(updates.getName());
        }
        if (updates.getBirthDate() != null) {
            actor.setBirthDate(updates.getBirthDate());
        }
        return actorRepository.save(actor);
    }

    // Delete actor, optionally force delete relationships
    public void delete(Long id, boolean force) {
        Actor actor = getById(id);

        if (!force && !actor.getMovies().isEmpty()) {
            throw new IllegalStateException(
                "Unable to delete actor '" + actor.getName() +
                "' as they are associated with " + actor.getMovies().size() + " movies."
            );
        }

        // Force deletion: remove actor from all associated movies
        if (force) {
            for (Movie movie : actor.getMovies()) {
                movie.getActors().remove(actor);
            }
        }

        actorRepository.delete(actor);
    }

    // Get all movies an actor has appeared in
    public List<Movie> getMovies(Long actorId) {
        Actor actor = getById(actorId);
        return List.copyOf(actor.getMovies());
    }
}
