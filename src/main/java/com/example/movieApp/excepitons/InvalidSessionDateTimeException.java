package com.example.movieApp.excepitons;

public class InvalidSessionDateTimeException extends RuntimeException {
    public InvalidSessionDateTimeException(String message) {
        super(message);
    }

    public InvalidSessionDateTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
