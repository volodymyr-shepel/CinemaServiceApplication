package com.example.movieApp.entities.appUser;

import com.example.movieApp.registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AppUser createUser(RegistrationRequest request, AppUserRole appUserRole) {
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        return new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                encodedPassword,
                appUserRole
        );
    }
}
