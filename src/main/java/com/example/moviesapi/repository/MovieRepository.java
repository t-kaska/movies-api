package com.example.moviesapi.repository;

import com.example.moviesapi.entity.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Get movies with optional filters (genre, year, actor) + pagination
    @Query("""
        SELECT DISTINCT m FROM Movie m
        LEFT JOIN m.genres g
        LEFT JOIN m.actors a
        WHERE (:genreId IS NULL OR g.id = :genreId)
            AND (:year IS NULL OR m.releaseYear = :year)
            AND (:actorId IS NULL OR a.id = :actorId)
    """)
    Page<Movie> findWithFilters(
            @Param("genreId") Long genreId,
            @Param("year") Integer year,
            @Param("actorId") Long actorId,
            Pageable pageable
    );

    // Search movies by title (case-insensitive partial match)
    Page<Movie> findByTitleIgnoreCaseContaining(String title, Pageable pageable);
}
