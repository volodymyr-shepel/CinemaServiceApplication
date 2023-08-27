package com.example.movieApp.registration;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PasswordValidator {
    public Boolean test(String password) {
        // Minimum 12 characters
        if (password.length() < 12) {
            return false;
        }

        // A combination of uppercase, lowercase, letters, and at least one special character
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{12,}$");
        return pattern.matcher(password).matches();

    }
}
