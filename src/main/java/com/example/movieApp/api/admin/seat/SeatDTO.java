package com.example.movieApp.api.admin.seat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SeatDTO {

    @NotNull
    private int rowNumber;

    @NotNull
    private int seatNumber;

    @NotNull
    private Long movieHallId;

    public SeatDTO(int rowNumber, int seatNumber, Long movieHallId) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.movieHallId = movieHallId;
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

    public Long getMovieHallId() {
        return movieHallId;
    }

    public void setMovieHallId(Long movieHallId) {
        this.movieHallId = movieHallId;
    }
}
