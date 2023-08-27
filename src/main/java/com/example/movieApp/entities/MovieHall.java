package com.example.movieApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
public class MovieHall {
    @Id
    @GeneratedValue
    private Long movieHallId;

    @NotNull
    @Min(value = 1,message = "Movie hall capacity should be at least 1")
    private int capacity;

    // since it covers only one physical cinema, that's why it should be unique
    @NotNull
    @Min(value = 0,message = "Movie hall number should be >=0")
    @Column(unique = true)
    private int movieHallNumber;

    @NotBlank
    //provides short description of movieHall. maybe specific technologies etc.
    private String description;





    // Specify on delete cascade
    @OneToMany(mappedBy = "movieHall", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MovieSession> sessions;

    @OneToMany(mappedBy = "movieHall", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SeatPricing> seatPricing;

    @OneToMany(mappedBy = "movieHall", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Seat> seats;




    public Long getMovieHallId() {
        return movieHallId;
    }


    public void setMovieHallId(Long movieHallId) {
        this.movieHallId = movieHallId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public MovieHall(int capacity, int movieHallNumber, String description) {
        this.capacity = capacity;
        this.movieHallNumber = movieHallNumber;
        this.description = description;
    }


    public MovieHall(){

    }

    public int getMovieHallNumber() {
        return movieHallNumber;
    }
}
