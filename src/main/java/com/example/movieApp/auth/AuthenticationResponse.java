package com.example.movieApp.auth;


public class AuthenticationResponse {


    private final String email;
    private final String accessToken;

    public AuthenticationResponse(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "email='" + email + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }


}
