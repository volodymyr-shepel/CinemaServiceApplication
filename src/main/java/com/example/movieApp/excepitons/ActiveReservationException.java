package com.example.movieApp.excepitons;

public class ActiveReservationException extends RuntimeException{
    public ActiveReservationException(String message) {
        super(message);
    }

    public ActiveReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}
