package com.example.moviesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// Exception thrown when a requested resource (e.g., Actor, Movie, Genre) is not found.

@ResponseStatus(HttpStatus.NOT_FOUND)  // automatically returns 404

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
