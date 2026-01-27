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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public MovieService(
            MovieRepository movieRepository,
            ActorRepository actorRepository,
            GenreRepository genreRepository
    ) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    // CREATE
    public Movie create(Movie movie) {

        resolveActors(movie);
        resolveGenres(movie);

        return movieRepository.save(movie);
    }

    // GET ALL / FILTERED (PAGINATED)
    public Page<Movie> getAll(
            Long genreId,
            Integer year,
            Long actorId,
            Pageable pageable
    ) {
        return movieRepository.findWithFilters(
                genreId,
                year,
                actorId,
                pageable
        );
    }

    // GET BY ID
    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Movie not found with id: " + id));
    }

    // PATCH
    public Movie update(Long id, Movie updates) {
        Movie movie = getById(id);

        if (updates.getTitle() != null)
            movie.setTitle(updates.getTitle());

        if (updates.getReleaseYear() != null)
            movie.setReleaseYear(updates.getReleaseYear());

        if (updates.getDuration() != null)
            movie.setDuration(updates.getDuration());

        if (updates.getActors() != null && !updates.getActors().isEmpty()) {
            movie.getActors().clear();
            resolveActors(updates);
            movie.getActors().addAll(updates.getActors());
        }

        if (updates.getGenres() != null && !updates.getGenres().isEmpty()) {
            movie.getGenres().clear();
            resolveGenres(updates);
            movie.getGenres().addAll(updates.getGenres());
        }

        return movieRepository.save(movie);
    }

    // DELETE
    public void deleteMovie(Long id, boolean force) {
        Movie movie = getById(id);

        movie.getActors().forEach(actor -> actor.getMovies().remove(movie));
        movie.getActors().clear();

        movie.getGenres().forEach(genre -> genre.getMovies().remove(movie));
        movie.getGenres().clear();

        movieRepository.delete(movie);
    }

    // ACTORS IN MOVIE
    public List<Actor> getActors(Long movieId) {
        return List.copyOf(getById(movieId).getActors());
    }

    // SEARCH (PAGINATED)
    public Page<Movie> search(String title, Pageable pageable) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Search title must not be empty");
        }
        return movieRepository.findByTitleIgnoreCaseContaining(title, pageable);
    }

    // ---------- helpers ----------

    private void resolveActors(Movie movie) {
        if (movie.getActors() == null) return;

        Set<Actor> resolvedActors = new HashSet<>();
        for (Actor actor : movie.getActors()) {
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

    private void resolveGenres(Movie movie) {
        if (movie.getGenres() == null) return;

        Set<Genre> resolvedGenres = new HashSet<>();
        for (Genre genre : movie.getGenres()) {
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
}
