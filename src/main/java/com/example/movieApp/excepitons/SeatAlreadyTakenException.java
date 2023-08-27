package com.example.movieApp.excepitons;

public class SeatAlreadyTakenException extends RuntimeException{
    public SeatAlreadyTakenException(String message) {
        super(message);
    }

    public SeatAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
