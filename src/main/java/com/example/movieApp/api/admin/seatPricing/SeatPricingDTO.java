package com.example.movieApp.api.admin.seatPricing;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SeatPricingDTO {

    @NotNull
    @JsonProperty("movieHallId")
    private Long movieHallId;


    @NotNull
    @JsonProperty("movieId")
    private Long movieId;

    @NotNull
    @Min(value = 0,message = "Price for ticket can not be less than 0")
    @JsonProperty("price")
    private BigDecimal price;

    public SeatPricingDTO(Long movieHallId, Long movieId, BigDecimal price) {
        this.movieHallId = movieHallId;
        this.movieId = movieId;
        this.price = price;
    }
    public SeatPricingDTO(){

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
