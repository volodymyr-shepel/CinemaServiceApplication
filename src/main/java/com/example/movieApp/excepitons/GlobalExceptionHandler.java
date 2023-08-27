package com.example.movieApp.excepitons;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(value = {
            InvalidEmailFormatException.class,
            InvalidPasswordFormatException.class,
            InvalidTokenException.class,
            UserNotFoundException.class,
            EmailAlreadyTakenException.class,
            EntityNotFoundException.class,
            InstanceNotFoundException.class,
            SeatAlreadyTakenException.class,
            ActiveReservationException.class,
            InvalidReservationTimeException.class,
            ValidationException.class

    })
    public ResponseEntity<Object> handleCustomExceptions(RuntimeException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExcepiton apiException = new ApiExcepiton(

                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
        );

        return new ResponseEntity<>(apiException,badRequest);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = "The data you are trying to insert violates a unique constraint.";

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExcepiton apiException = new ApiExcepiton(

                errorMessage,
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
        );

        return new ResponseEntity<>(apiException,badRequest);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getConstraintViolations().forEach(violation ->
                errorMessage.append(violation.getMessage()).append("; ")
        );
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExcepiton apiException = new ApiExcepiton(

                errorMessage.toString(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
        );

        return new ResponseEntity<>(apiException, badRequest);

    }
}
