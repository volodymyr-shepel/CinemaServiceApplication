package com.example.movieApp.excepitons;

public class InstanceNotFoundException extends RuntimeException{
    public InstanceNotFoundException(String message){
        super(message);
    }
    public InstanceNotFoundException(String message,Throwable cause){
        super(message, cause);
    }
}
