package com.example.movieApp.api.admin.movieSession;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entities.MovieHall;
import com.example.movieApp.entities.MovieSession;
import com.example.movieApp.entityRepositories.MovieHallRepository;
import com.example.movieApp.entityRepositories.MovieRepository;
import com.example.movieApp.entityRepositories.MovieSessionRepository;
import com.example.movieApp.excepitons.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminMovieSessionService {

    private final MovieHallRepository movieHallRepository;
    private final MovieRepository movieRepository;

    private final MovieSessionRepository movieSessionRepository;

    private final MovieSessionValidator movieSessionValidator;

    @Autowired
    public AdminMovieSessionService(MovieHallRepository movieHallRepository, MovieRepository movieRepository, MovieSessionRepository movieSessionRepository, MovieSessionValidator movieSessionValidator) {
        this.movieHallRepository = movieHallRepository;
        this.movieRepository = movieRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.movieSessionValidator = movieSessionValidator;
    }
    // get request getAllMovieSessions
    public List<MovieSession> getAllMovieSessions() {
        return movieSessionRepository.findAll();
    }

    // post request addMovieSession
    public ResponseEntity<MovieSession> addMovieSession(MovieSessionDTO movieSessionDTO) {

        movieSessionDTO.setEndsAt(calculateEndsAt(movieSessionDTO.getStartsAt(),movieSessionDTO.getMovieId()));

        movieSessionValidator.validate(movieSessionDTO);

        MovieSession movieSession = createMovieSession(movieSessionDTO);

        movieSessionRepository.save(movieSession);

        return ResponseEntity.ok(movieSession);

    }

    // Delete request removeMovieSession
    public ResponseEntity<Long> removeMovieSession(Long movieSessionId) {
        Optional<MovieSession> movieSessionOptional = movieSessionRepository.findById(movieSessionId);
        if (movieSessionOptional.isPresent()) {
            movieSessionRepository.deleteById(movieSessionId);
            return ResponseEntity.ok(movieSessionId); // Deletion successful, return the ID
        } else {
            String errorMessage = "Movie session with ID " + movieSessionId + " not found.";

            return ResponseEntity.notFound().header("X-Error-Message", errorMessage).build(); // Movie not found, return 404 Not Found
        }
    }


    // helper methods
    private MovieSession createMovieSession(MovieSessionDTO movieSessionDTO) {
        Movie movie = findMovieById(movieSessionDTO.getMovieId());
        MovieHall movieHall = findMovieHallById(movieSessionDTO.getMovieHallId());
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setMovieHall(movieHall);
        movieSession.setStartsAt(movieSessionDTO.getStartsAt());
        movieSession.setEndsAt(movieSessionDTO.getEndsAt());
        return movieSession;
    }

    private MovieHall findMovieHallById(Long movieHallId) {
        return movieHallRepository.findById(movieHallId).orElseThrow(() -> new InstanceNotFoundException("The movie hall with provided id does not exist"));
    }

    private Movie findMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new InstanceNotFoundException("The movie with provided id does not exist"));
    }

    // used to calculate the end time of movie session
    private LocalDateTime calculateEndsAt(LocalDateTime startsAt, Long movieId) {
        LocalDateTime endTime = startsAt.plusMinutes(findMovieById(movieId).getMovieDuration());
        return endTime;
    }





}
