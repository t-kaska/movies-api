package com.example.moviesapi.service;

import com.example.moviesapi.entity.Actor;
import com.example.moviesapi.entity.Genre;
import com.example.moviesapi.entity.Movie;
import com.example.moviesapi.exception.ResourceNotFoundException;
import com.example.moviesapi.repository.ActorRepository;
import com.example.moviesapi.repository.GenreRepository;
import com.example.moviesapi.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    // Create a movie
    public Movie create(Movie movie) {

        // Resolve actors by ID
        if (movie.getActors() != null) {
            Set<Actor> resolvedActors = new HashSet<>();
            for (Actor actor : movie.getActors()) {
                    Actor existingActor = actorRepository.findById(actor.getId())
                        .orElseThrow(() ->
                            new ResourceNotFoundException("Actor not found with id: " + actor.getId()
                            )
                        );
                    resolvedActors.add(existingActor);
            }
            movie.getActors().clear();
            movie.getActors().addAll(resolvedActors);
        }

        // Resolve genres by ID
        if (movie.getGenres() != null) {
            Set<Genre> resolvedGenres = new HashSet<>();
            for (Genre genre : movie.getGenres()) {
                Genre existingGenre = genreRepository.findById(genre.getId())
                    .orElseThrow(() ->
                        new ResourceNotFoundException("Genre not found with id: " + genre.getId()
                        )
                    );
                resolvedGenres.add(existingGenre);
            }
            movie.getGenres().clear();
            movie.getGenres().addAll(resolvedGenres);
        }
        return movieRepository.save(movie);
    }

    // Get all movies (paginated)
    public Page<Movie> getAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    // Get movie by ID
    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    // Filter movies by genre, release year, or actor
    public List<Movie> filter(Long genreId, Integer year, Long actorId) {
        if (genreId != null) return movieRepository.findByGenreId(genreId);
        if (year != null) return movieRepository.findByReleaseYear(year);
        if (actorId != null) return movieRepository.findByActorId(actorId);
        return movieRepository.findAll();
    }

    // Partial update (PATCH)
    public Movie update(Long id, Movie updates) {
        Movie movie = getById(id);

        if (updates.getTitle() != null) movie.setTitle(updates.getTitle());
        if (updates.getReleaseYear() != null) movie.setReleaseYear(updates.getReleaseYear());
        if (updates.getDuration() != null) movie.setDuration(updates.getDuration());

        // Update actors (add/remove)
        if (updates.getActors() != null && !updates.getActors().isEmpty()) {
            Set<Actor> resolvedActors = new HashSet<>();

            for (Actor actor : updates.getActors()) {
                Actor existingActor = actorRepository.findById(actor.getId())
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Actor not found with id: " + actor.getId()
                        )
                    );
                resolvedActors.add(existingActor);
            }

            movie.getActors().clear();
            movie.getActors().addAll(resolvedActors);
        }

        // Update genres
        if (updates.getGenres() != null && !updates.getGenres().isEmpty()) {
            Set<Genre> resolvedGenres = new HashSet<>();
            for (Genre genre : updates.getGenres()) {
                Genre existingGenre = genreRepository.findById(genre.getId())
                    .orElseThrow(() ->
                        new ResourceNotFoundException(
                            "Genre not found with id: " + genre.getId()
                        )
                    );
                resolvedGenres.add(existingGenre);
            }

            movie.getGenres().clear();
            movie.getGenres().addAll(resolvedGenres);
        }

        return movieRepository.save(movie);
    }

    // Get actors in a movie
    public List<Actor> getActors(Long movieId) {
        Movie movie = getById(movieId);
        return List.copyOf(movie.getActors());
    }

    // Search movies by title (case-insensitive)
    public List<Movie> search(String title) {
        return movieRepository.findByTitleIgnoreCaseContaining(title);
    }

    // Delete movie
    public void deleteMovie(Long id, boolean force) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Movie not found with id: " + id));

        // Always remove relationships first
        movie.getActors().forEach(actor -> actor.getMovies().remove(movie));
        movie.getActors().clear();

        movie.getGenres().forEach(genre -> genre.getMovies().remove(movie));
        movie.getGenres().clear();

        movieRepository.delete(movie);
    }

    // Get sorted list
    public List<Movie> getAllSortedById() {
        return movieRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
}


}
