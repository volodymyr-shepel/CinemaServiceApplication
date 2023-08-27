package com.example.movieApp.api.admin.movieHall;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieHallDTO {

    @NotNull
    @Min(value = 1,message = "Movie hall capacity should be at least 1")
    @JsonProperty("movieHallCapacity")
    private int movieHallCapacity;


    @NotNull
    @Min(value = 0,message = "Movie hall number should be >=0")
    @JsonProperty("movieHallNumber")
    private int movieHallNumber;

    @JsonProperty("movieHallDescription")
    @NotBlank(message = "Movie hall description can not be blank")
    private String movieHallDescription;


    @JsonProperty("seatsPerRow")
    @NotNull
    @Min(value = 1,message = "Number of seats per row should be >= 1")
    private int seatsPerRow;


    public MovieHallDTO(int movieHallCapacity, int movieHallNumber, String movieHallDescription, int seatsPerRow) {
        this.movieHallCapacity = movieHallCapacity;
        this.movieHallNumber = movieHallNumber;
        this.movieHallDescription = movieHallDescription;
        this.seatsPerRow = seatsPerRow;
    }

    public int getMovieHallCapacity() {
        return movieHallCapacity;
    }

    public void setMovieHallCapacity(int movieHallCapacity) {
        this.movieHallCapacity = movieHallCapacity;
    }

    public int getMovieHallNumber() {
        return movieHallNumber;
    }

    public void setMovieHallNumber(int movieHallNumber) {
        this.movieHallNumber = movieHallNumber;
    }

    public String getMovieHallDescription() {
        return movieHallDescription;
    }

    public void setMovieHallDescription(String movieHallDescription) {
        this.movieHallDescription = movieHallDescription;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }
}
