package com.example.moviesapi.service;

import com.example.moviesapi.entity.Genre;
import com.example.moviesapi.exception.ResourceNotFoundException;
import com.example.moviesapi.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Create a new genre
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    // Get all genres
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    // Get genre by ID
    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found with id: " + id));
    }

    // Update genre name (PATCH)
    public Genre update(Long id, Genre updates) {
        Genre genre = getById(id);
        if (updates.getName() != null) {
            genre.setName(updates.getName());
        }
        return genreRepository.save(genre);
    }

    // Delete genre, optionally force delete relationships
    public void delete(Long id, boolean force) {
        Genre genre = getById(id);

        if (!force && !genre.getMovies().isEmpty()) {
            throw new IllegalStateException(
                "Cannot delete genre '" + genre.getName() +
                "' because it has " + genre.getMovies().size() + " associated movies."
            );
        }

        // Force deletion: remove genre from all associated movies
        if (force) {
            genre.getMovies().forEach(movie -> movie.getGenres().remove(genre));
        }

        genreRepository.delete(genre);
    }
}
