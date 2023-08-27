package com.example.movieApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"rowNumber", "seatNumber", "movie_hall_id"})
        }
)

public class Seat {
    @Id
    @GeneratedValue
    private Long seatId;

    @NotNull
    @Min(value = 1,message = "row number must be >= 1")
    private int rowNumber;

    @NotNull
    @Min(value =  1,message = "seat number must be >= 1")
    private int seatNumber;


    @ManyToOne
    @NotNull
    @JoinColumn(name = "movie_hall_id")
    private MovieHall movieHall;


    public Seat(int rowNumber, int seatNumber, MovieHall movieHall) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.movieHall = movieHall;
    }

    public Seat(){

    }
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public MovieHall getMovieHall() {
        return movieHall;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public void setMovieHall(MovieHall movieHall) {
        this.movieHall = movieHall;
    }


}
