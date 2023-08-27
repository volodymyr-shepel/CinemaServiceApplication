package com.example.movieApp.entities;

import com.example.movieApp.api.admin.movieSession.MovieSessionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
public class MovieSession {

    @Id
    @GeneratedValue
    private Long movieSessionId;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "movie_hall_id")
    @NotNull
    private MovieHall movieHall;

    @NotNull
    private LocalDateTime startsAt;

    @NotNull
    private LocalDateTime endsAt;




    public MovieSession(Movie movie, LocalDateTime startsAt, LocalDateTime endsAt, MovieHall movieHall) {
        this.movie = movie;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.movieHall = movieHall;
    }
    public MovieSession(){

    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(LocalDateTime startsAt) {
        this.startsAt = startsAt;
    }

    public LocalDateTime getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(LocalDateTime endsAt) {
        this.endsAt = endsAt;
    }

    public MovieHall getMovieHall() {
        return movieHall;
    }

    public void setMovieHall(MovieHall movieHall) {
        this.movieHall = movieHall;
    }
}
