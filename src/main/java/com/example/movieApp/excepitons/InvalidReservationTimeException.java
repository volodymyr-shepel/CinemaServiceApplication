package com.example.movieApp.excepitons;

public class InvalidReservationTimeException extends RuntimeException {
    public InvalidReservationTimeException(String message) {
        super(message);
    }

    public InvalidReservationTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
