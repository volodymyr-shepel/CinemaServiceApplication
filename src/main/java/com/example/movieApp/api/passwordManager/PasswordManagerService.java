package com.example.movieApp.api.passwordManager;

import com.example.movieApp.entities.appUser.AppUser;
import com.example.movieApp.entities.appUser.AppUserRepository;
import com.example.movieApp.registration.PasswordValidator;
import com.example.movieApp.securityConfiguration.PasswordEncoder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class PasswordManagerService {
    private final AppUserRepository appUserRepository;

    private final PasswordValidator passwordValidator;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordManagerService(AppUserRepository appUserRepository, PasswordValidator passwordValidator, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordValidator = passwordValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<String> changePassword(ChangePasswordRequest changePasswordRequest) {
        AppUser authenticatedUser = getAuthenticatedUser();

        String validationErrorMessage = validatePasswordChange(changePasswordRequest, authenticatedUser);
        if (validationErrorMessage != null) {
            return ResponseEntity.badRequest().body(validationErrorMessage);
        }

        updatePassword(authenticatedUser, changePasswordRequest.getNewPassword());

        appUserRepository.save(authenticatedUser);

        return ResponseEntity.ok("Password has been updated successfully");
    }

    private String validatePasswordChange(ChangePasswordRequest changePasswordRequest, AppUser authenticatedUser) {
        if (!passwordEncoder.bCryptPasswordEncoder().matches(changePasswordRequest.getOldPassword(), authenticatedUser.getPassword())) {
            return "The old password does not match";
        }

        if (!passwordValidator.test(changePasswordRequest.getNewPassword())) {
            return "New password does not satisfy security criteria";
        }

        if (passwordEncoder.bCryptPasswordEncoder().matches(changePasswordRequest.getNewPassword(), authenticatedUser.getPassword())) {
            return "New password cannot be the same as the old one";
        }

        return null; // No validation error
    }

    private void updatePassword(AppUser user, String newPassword) {
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(newPassword));
    }

    private AppUser getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (AppUser) authentication.getPrincipal();
        } else {
            throw new IllegalStateException("The user is not authenticated");
        }
    }


}
