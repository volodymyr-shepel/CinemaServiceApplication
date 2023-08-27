package com.example.movieApp.registration;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator {

    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@(.+)$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public Boolean test(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
