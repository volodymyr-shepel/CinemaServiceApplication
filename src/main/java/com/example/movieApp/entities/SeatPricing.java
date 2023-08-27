package com.example.movieApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id", "movie_hall_id"}))
public class SeatPricing {
    @Id
    @GeneratedValue
    private Long pricingId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;


    @ManyToOne
    @JoinColumn(name = "movie_hall_id")
    @NotNull
    private MovieHall movieHall;


    @NotNull
    @Min(value = 1,message = "Price can not be less than 0")
    private BigDecimal price;

    public SeatPricing(Movie movie, MovieHall movieHall, BigDecimal price) {
        this.movie = movie;
        this.movieHall = movieHall;
        this.price = price;
    }
    public SeatPricing(){

    }
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieHall getMovieHall() {
        return movieHall;
    }

    public void setMovieHall(MovieHall movieHall) {
        this.movieHall = movieHall;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getPricingId() {
        return pricingId;
    }
}
