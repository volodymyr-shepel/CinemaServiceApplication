package com.example.movieApp.excepitons;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExcepiton {
    private final String message;

    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;

    public String getMessage() {
        return message;
    }



    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public ApiExcepiton(
            String message,
            HttpStatus httpStatus,
            ZonedDateTime timestamp) {

        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
