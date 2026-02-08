# Movie Database API

A REST API for managing a movie database built with Spring Boot and JPA. This API allows users to manage movies, genres, and actors with full CRUD operations, filtering capabilities, pagination, and search functionality.

## Project Overview

This project was developed for a local film society to digitize their movie records. The API provides endpoints to manage movies, genres, and actors, including their many-to-many relationships. Users can add new movies, update existing entries, search through the collection, and retrieve information filtered by various criteria.

## Technologies Used

- **Java 21** - Programming language
- **Spring Boot 3.5.9** - Application framework
- **Spring Data JPA** - Data persistence
- **Hibernate 6.6.39** - ORM implementation
- **SQLite 3.50.3** - Database
- **Maven** - Build and dependency management

## Setup and Installation

### Prerequisites

Before running this project, ensure you have the following installed:

- Java Development Kit (JDK) 21 or higher
- Maven 3.6+ (or use the included Maven wrapper)

### Installation Steps

1. Clone the repository:
```bash
git clone <repository-url>
cd movie-api
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

Note: On Mac, if you get a "permission denied" error, first run:
```bash
chmod +x mvnw
```

4. The API will be available at `http://localhost:8080`

### Database Configuration

The application uses SQLite as its database. The database file (`movies.db`) is automatically created in the project root directory when the application starts for the first time. Sample data is automatically loaded on first run.

To reset the database and reload sample data, delete the `movies.db` file and restart the application.

## API Endpoints

### Movies

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/movies` | Retrieve all movies (paginated) |
| GET | `/api/movies?page=0&size=100` | Retrieve all movies (on one page) |
| GET | `/api/movies/{id}` | Retrieve a specific movie by ID |
| GET | `/api/movies?genre={genreId}` | Filter movies by genre |
| GET | `/api/movies?year={releaseYear}` | Filter movies by release year |
| GET | `/api/movies?actor={actorId}` | Filter movies by actor |
| GET | `/api/movies/search?title={title}` | Search movies by title (case-insensitive) |
| GET | `/api/movies/{id}/actors` | Get all actors in a specific movie |
| POST | `/api/movies` | Create a new movie |
| PATCH | `/api/movies/{id}` | Partially update an existing movie |
| DELETE | `/api/movies/{id}` | Delete a movie |
| DELETE | `/api/movies/{id}?force=true` | Force delete a movie with relationships |

### Genres

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/genres` | Retrieve all genres |
| GET | `/api/genres/{id}` | Retrieve a specific genre by ID |
| POST | `/api/genres` | Create a new genre |
| PATCH | `/api/genres/{id}` | Partially update an existing genre |
| DELETE | `/api/genres/{id}` | Delete a genre |
| DELETE | `/api/genres/{id}?force=true` | Force delete a genre with relationships |

### Actors

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/actors` | Retrieve all actors |
| GET | `/api/actors/{id}` | Retrieve a specific actor by ID |
| GET | `/api/actors?name={name}` | Filter actors by name |
| POST | `/api/actors` | Create a new actor |
| PATCH | `/api/actors/{id}` | Partially update an existing actor |
| DELETE | `/api/actors/{id}` | Delete an actor |
| DELETE | `/api/actors/{id}?force=true` | Force delete an actor with relationships |

## Usage Guide

### Pagination

All GET endpoints that return multiple entities support pagination using the following query parameters:

- `page` - Page number (0-indexed, default: 0)
- `size` - Number of items per page (default: 10)

Example:
```
GET /api/movies?page=0&size=10
```

### Creating a Movie

Send a POST request to `/api/movies` with a JSON body:

```json
{
    "title": "Interstellar",
    "releaseYear": 2014,
    "duration": 169
}
```

### Updating a Movie

Send a PATCH request to `/api/movies/{id}` with the fields you want to update:

```json
{
    "title": "Interstellar - Director's Cut"
}
```

### Searching Movies

Use the search endpoint for case-insensitive partial matching:

```
GET /api/movies/search?title=matrix
```

This will return all movies containing "matrix" in their title.

### Filtering Movies

Filter movies by various criteria:

```
GET /api/movies?genre=1      # Movies in genre with ID 1
GET /api/movies?year=2010    # Movies released in 2010
GET /api/movies?actor=1      # Movies featuring actor with ID 1
```

## Data Model

### Entities and Relationships

The API manages three main entities with the following relationships:

**Movie** contains title, release year, and duration. **Genre** contains a unique name. **Actor** contains name and birth date.

The relationships between entities are many-to-many: a movie can have multiple genres and a genre can have multiple movies; similarly, a movie can have multiple actors and an actor can be in multiple movies.

### Entity Fields

**Movie:**
- `id` (Long) - Primary key, auto-generated
- `title` (String) - Required, movie title
- `releaseYear` (Integer) - Required, must be 1888 or later
- `duration` (Integer) - Required, must be at least 1 minute

**Genre:**
- `id` (Long) - Primary key, auto-generated
- `name` (String) - Required, unique, max 50 characters

**Actor:**
- `id` (Long) - Primary key, auto-generated
- `name` (String) - Required
- `birthDate` (LocalDate) - Required, ISO 8601 format (YYYY-MM-DD)

## Error Handling

The API returns appropriate HTTP status codes and error messages:

| Status Code | Description |
|-------------|-------------|
| 200 OK | Successful GET or PATCH request |
| 201 Created | Successfully created a new resource |
| 204 No Content | Successfully deleted a resource |
| 400 Bad Request | Invalid input or validation error |
| 404 Not Found | Resource not found |

Error responses include a descriptive message explaining what went wrong.

### Deletion with Relationships

By default, deleting an entity with existing relationships is not allowed. The API will return an error message such as "Cannot delete genre 'Action' because it has 15 associated movies."

To force delete an entity and remove all its relationships, use the `force=true` query parameter:

```
DELETE /api/genres/1?force=true
```

## Sample Data

The database is pre-populated with sample data including:

**5 Genres:** Action, Comedy, Sci-Fi, Drama, Thriller

**15 Actors:** Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Leonardo DiCaprio, Tom Hanks, Morgan Freeman, Brad Pitt, Natalie Portman, Scarlett Johansson, Robert Downey Jr., Chris Evans, Emma Stone, Ryan Gosling, Meryl Streep, Denzel Washington

**20 Movies:** Matrix (1999), Inception (2010), Forrest Gump (1994), The Shawshank Redemption (1994), Fight Club (1999), Black Swan (2010), Lost in Translation (2003), Iron Man (2008), The Avengers (2012), La La Land (2016), The Devil Wears Prada (2006), Training Day (2001), The Departed (2006), Cast Away (2000), Se7en (1995), John Wick (2014), Captain America: The Winter Soldier (2014), The Wolf of Wall Street (2013), Crazy, Stupid, Love (2011), V for Vendetta (2005)

The sample data demonstrates various relationship scenarios including movies with multiple genres, actors appearing in multiple movies, and release years spanning from 1994 to 2016.

## Testing with Postman

To test the API using Postman, first ensure the application is running at `http://localhost:8080`. Create requests for each endpoint, setting the body type to "raw" and format to "JSON" for POST and PATCH requests. Test all CRUD operations for each entity, then verify filtering and search functionality work correctly. Also test error scenarios such as invalid data, non-existent resources, and deletion of entities with existing relationships.

## Project Structure

```
movie-api/
├── .mvn/                    # Maven wrapper configuration
├── src/
│   ├── main/
│   │   ├── java/com/example/moviesapi/
│   │   │   ├── controller/
│   │   │   │   ├── ActorController.java
│   │   │   │   ├── GenreController.java
│   │   │   │   └── MovieController.java
│   │   │   ├── entity/
│   │   │   │   ├── Actor.java
│   │   │   │   ├── Genre.java
│   │   │   │   └── Movie.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── repository/
│   │   │   │   ├── ActorRepository.java
│   │   │   │   ├── GenreRepository.java
│   │   │   │   └── MovieRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ActorService.java
│   │   │   │   ├── GenreService.java
│   │   │   │   └── MovieService.java
│   │   │   ├── DataLoader.java
│   │   │   └── MoviesApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/moviesapi/
│           └── MoviesApiApplicationTests.java
├── target/                  # Compiled output (generated)
├── .gitattributes           # Git attributes configuration
├── .gitignore               # Git ignore rules
├── movies.db                # SQLite database file (generated)
├── mvnw                     # Maven wrapper script (Mac/Linux)
├── mvnw.cmd                 # Maven wrapper script (Windows)
├── pom.xml                  # Maven project configuration
└── README.md                # Project documentation
```

## Features Implemented

### Core Features

The API implements full CRUD operations for Movies, Genres, and Actors. Many-to-many relationships between entities are properly managed. Movies can be filtered by genre, release year, or actor. The API provides endpoints to get all actors in a specific movie and filter actors by name. Input validation ensures data integrity with appropriate error messages, and global exception handling provides consistent error responses.

### Extra Features

**Pagination:** 
All list endpoints support pagination with `page` and `size` parameters, returning paginated results with metadata about total elements and pages.

**Basic Search:** 
Case-insensitive partial match search for movies by title is available through the `/api/movies/search?title={title}` endpoint.

**Combined Filtering:** 
Filter movies by multiple criteria simultaneously using query parameters: 
`genre`, `year` and/or `actor`.

Example:
`/api/movies?genre=4&year=2000&actor=5`

Pagination is supported using the `page` and `size` parameters: 
`/api/movies?genre=4&year=2000&actor=5&page=0&size=10`

## API Testing with Postman

A Postman collection is included in the `postman/` directory.

### How to use:
1. Open Postman
2. Click Import
3. Select `postman/Movie-Database-API.postman_collection.json` from Your project
4. (Optional) Import the environment file
5. Run requests against `http://localhost:8080`

## Authors

Tõnis Kaska, Elisabeth Kuulmann


