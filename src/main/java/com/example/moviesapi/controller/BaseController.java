package com.example.moviesapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class BaseController {

    @GetMapping("/api")
    public Map<String, Object> apiHome() {
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("name", "Movie Database API");
        response.put("version", "1.0.0");
        response.put("status", "running");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("genres", "/api/genres");
        endpoints.put("movies", "/api/movies");
        endpoints.put("actors", "/api/actors");
        endpoints.put("search movies by title", "/api/movies/search?title={title}");
        endpoints.put("filter movies by genre", "/api/movies?genre={genreId}");
        endpoints.put("filter movies by year", "/api/movies?year={releaseYear}");
        endpoints.put("filter movies by actor", "/api/movies?actor={actorId}");

        response.put("endpoints", endpoints);

        return response;
    }
}
