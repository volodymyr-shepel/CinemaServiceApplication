package com.example.movieApp.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AuthenticationRequest {

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @NotNull @Email
    public String email;
    @NotNull
    public String password;

}
