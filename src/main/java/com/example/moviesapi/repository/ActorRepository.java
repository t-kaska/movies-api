package com.example.moviesapi.repository;

import com.example.moviesapi.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    // Custom query: case-insensitive search by actor name (partial match)
    List<Actor> findByNameIgnoreCaseContaining(String name);
}
