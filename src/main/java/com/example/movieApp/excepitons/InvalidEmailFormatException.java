package com.example.movieApp.excepitons;

public class InvalidEmailFormatException extends RuntimeException{
    public InvalidEmailFormatException(String message){
        super(message);
    }
    public InvalidEmailFormatException(String message,Throwable cause){
        super(message, cause);
    }
}
