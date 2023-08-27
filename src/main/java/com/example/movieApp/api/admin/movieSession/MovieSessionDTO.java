package com.example.movieApp.api.admin.movieSession;

import com.example.movieApp.entities.Movie;
import com.example.movieApp.entityRepositories.MovieRepository;
import com.example.movieApp.excepitons.InstanceNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class MovieSessionDTO {

    @Autowired
    @JsonIgnore
    private MovieRepository movieRepository;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startsAt;

    // will be set up in service class
    @JsonIgnore
    private LocalDateTime endsAt;

    @NotNull
    private Long movieHallId;

    @NotNull
    private Long movieId;

    public MovieSessionDTO(){

    }

    public MovieSessionDTO(LocalDateTime startsAt, Long movieHallId, Long movieId) {
        this.startsAt = startsAt;
        this.movieHallId = movieHallId;
        this.movieId = movieId;
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

    public Long getMovieHallId() {
        return movieHallId;
    }

    public void setMovieHallId(Long movieHallId) {
        this.movieHallId = movieHallId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }


    private Movie findMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new InstanceNotFoundException("The movie with provided id does not exist"));
    }


}
