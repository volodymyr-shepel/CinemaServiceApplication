package com.example.movieApp.api.unauthorizedUser;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.MovieSession;
import com.example.movieApp.entityRepositories.MovieRepository;
import com.example.movieApp.entityRepositories.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UnauthorizedUserService {
    private final MovieRepository movieRepository;
    private final MovieSessionRepository movieSessionRepository;

    public UnauthorizedUserService(MovieRepository movieRepository, MovieSessionRepository movieSessionRepository) {
        this.movieRepository = movieRepository;
        this.movieSessionRepository = movieSessionRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<MovieSession> getMovieSessionsByDate(LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(23, 59, 59);
        return movieSessionRepository.findAllByStartsAtBetween(startDateTime, endDateTime);
    }

}
