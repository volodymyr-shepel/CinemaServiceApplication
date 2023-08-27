package com.example.movieApp.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDTO {
    @JsonProperty("seatId")
    private final Long seatId;


    @JsonProperty("movieSessionId")
    private final Long movieSessionId;

    public BookingDTO(Long seatId, Long movieSessionId, Long appUserId) {
        this.seatId = seatId;
        this.movieSessionId = movieSessionId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }


}
