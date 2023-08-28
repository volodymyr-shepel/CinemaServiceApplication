package com.example.movieApp.api.passwordManager;

import com.example.movieApp.appUser.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// It is not for situation when user forgets the password, but when the user wants to change it
@RestController
@RequestMapping(path = "/api/passwordManager")
public class PasswordManagerController {
    private final AppUserRepository appUserRepository;
    private final PasswordManagerService passwordManagerService;

    @Autowired
    public PasswordManagerController(AppUserRepository appUserRepository, PasswordManagerService passwordManagerService) {
        this.appUserRepository = appUserRepository;
        this.passwordManagerService = passwordManagerService;
    }

    @Transactional
    @PutMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return passwordManagerService.changePassword(changePasswordRequest);

    }
}
