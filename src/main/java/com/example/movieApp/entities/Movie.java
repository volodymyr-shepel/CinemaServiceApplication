package com.example.movieApp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "movieTitle"))
public class Movie {
    @Id
    @GeneratedValue
    private Long movieId;

    @NotBlank(message = "Movie title cannot be blank")
    @Column(unique = true)
    @JsonProperty("movieTitle")
    public String movieTitle;

    @NotBlank(message = "Movie description cannot be blank")
    @JsonProperty("movieDescription")
    private String movieDescription;

    @NotNull
    @Min(value = 1, message = "Movie duration must be greater than zero")
    @JsonProperty("movieDuration")
    private Integer movieDuration;


    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MovieSession> sessions;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SeatPricing> seatPricing;

    public Movie(String movieTitle, String movieDescription, Integer movieDuration) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDuration = movieDuration;
    }
    public Movie(){

    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public Integer getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(Integer movieDuration) {
        this.movieDuration = movieDuration;
    }
}
