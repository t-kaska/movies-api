package com.example.moviesapi.repository;

import com.example.moviesapi.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // No custom queries needed yet; basic CRUD comes from JpaRepository
}
