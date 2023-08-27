package com.example.movieApp.api.admin.movieSession;

import com.example.movieApp.entityRepositories.MovieHallRepository;
import com.example.movieApp.entityRepositories.MovieRepository;
import com.example.movieApp.entityRepositories.MovieSessionRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// Used to validate movieSessionDTO
@Component
public class MovieSessionValidator {
    private final MovieRepository movieRepository;
    private final MovieHallRepository movieHallRepository;
    private final MovieSessionRepository movieSessionRepository;

    @Autowired
    public MovieSessionValidator(
            MovieRepository movieRepository,
            MovieHallRepository movieHallRepository,
            MovieSessionRepository movieSessionRepository) {

        this.movieRepository = movieRepository;
        this.movieHallRepository = movieHallRepository;
        this.movieSessionRepository = movieSessionRepository;
    }


    public void validate(MovieSessionDTO movieSessionDTO) throws ValidationException {
        validateMovieExists(movieSessionDTO.getMovieId());
        validateMovieHallExists(movieSessionDTO.getMovieHallId());

        LocalDateTime sessionDateTime = movieSessionDTO.getStartsAt();
        validateSessionDateTimeNotBeforeCurrent(sessionDateTime);

        if (isMovieHallOccupied(movieSessionDTO)) {
            throw new ValidationException("Movie hall is already occupied during the specified time period.");
        }
    }

    private void validateMovieExists(Long movieId) throws ValidationException {
        if (!movieRepository.existsById(movieId)) {
            throw new ValidationException("Movie with provided id not found.");
        }
    }

    private void validateMovieHallExists(Long movieHallId) throws ValidationException {
        if (!movieHallRepository.existsById(movieHallId)) {
            throw new ValidationException("Movie hall with provided id not found.");
        }
    }

    private void validateSessionDateTimeNotBeforeCurrent(LocalDateTime sessionDateTime) throws ValidationException {
        if (sessionDateTime.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Session start time cannot be in the past.");
        }
    }

    private boolean isMovieHallOccupied(MovieSessionDTO movieSessionDTO){


        return movieSessionRepository
                .isMovieHallOccupiedDuringPeriod(movieSessionDTO.getMovieHallId(),movieSessionDTO.getStartsAt(),movieSessionDTO.getEndsAt());

    }
}