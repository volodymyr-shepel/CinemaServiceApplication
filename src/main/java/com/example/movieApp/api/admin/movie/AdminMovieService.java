package com.example.movieApp.api.admin.movie;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entityRepositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminMovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public AdminMovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public ResponseEntity<Movie> addMovie(Movie movie) {
        movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movie);
    }

    public ResponseEntity<Long> removeMovie(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()) {
            movieRepository.deleteById(movieId);
            return ResponseEntity.ok(movieId); // Deletion successful, return the ID
        } else {
            String errorMessage = "Movie with ID " + movieId + " not found.";

            return ResponseEntity.notFound().header("X-Error-Message", errorMessage).build(); // Movie not found, return 404 Not Found
        }
    }

    @Transactional
    public ResponseEntity<Movie> updateMovie(Long movieId, Movie updatedMovie) {

        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            updateMovieFields(movie, updatedMovie);
            return ResponseEntity.ok(movie);
        } else {
            String errorMessage = "Movie with ID " + movieId + " not found.";
            return ResponseEntity.notFound().header("X-Error-Message", errorMessage).build(); // Movie not found, return 404 Not Found

        }
    }

    @Transactional
    private void updateMovieFields(Movie existingMovie, Movie updatedMovie) {
        existingMovie.setMovieTitle(updatedMovie.getMovieTitle());
        existingMovie.setMovieDescription(updatedMovie.getMovieDescription());
        existingMovie.setMovieDuration(updatedMovie.getMovieDuration());
    }


}
