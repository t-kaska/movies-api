package com.example.moviesapi.repository;

import com.example.moviesapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Find movies by genre ID
    @Query("SELECT m FROM Movie m JOIN m.genres g WHERE g.id = :genreId")
    List<Movie> findByGenreId(Long genreId);

    // Find movies by release year
    List<Movie> findByReleaseYear(Integer releaseYear);

    // Find movies by actor ID
    @Query("SELECT m FROM Movie m JOIN m.actors a WHERE a.id = :actorId")
    List<Movie> findByActorId(Long actorId);

    // Search movies by title (case-insensitive partial match)
    List<Movie> findByTitleIgnoreCaseContaining(String title);
}
