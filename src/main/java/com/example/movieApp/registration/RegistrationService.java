package com.example.movieApp.registration;

import com.example.movieApp.entities.appUser.AppUser;
import com.example.movieApp.entities.appUser.AppUserRepository;
import com.example.movieApp.entities.appUser.AppUserRole;
import com.example.movieApp.entities.appUser.AppUserService;
import com.example.movieApp.email.EmailService;
import com.example.movieApp.excepitons.InvalidEmailFormatException;
import com.example.movieApp.excepitons.InvalidPasswordFormatException;
import com.example.movieApp.excepitons.InvalidTokenException;
import com.example.movieApp.excepitons.UserNotFoundException;
import com.example.movieApp.registration.confirmationToken.ConfirmationToken;
import com.example.movieApp.registration.confirmationToken.ConfirmationTokenRepository;
import com.example.movieApp.registration.confirmationToken.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {


    private final EmailValidator emailValidator;

    private final PasswordValidator passwordValidator;

    private final AppUserRepository appUserRepository;

    private final ConfirmationTokenRepository confirmationTokenRepository;


    private final ConfirmationTokenService confirmationTokenService;


    private final EmailService emailService;

    private final Environment environment;

    private final AppUserService appUserService;

    @Autowired
    public RegistrationService(
            EmailValidator emailValidator,
            PasswordValidator passwordValidator,
            AppUserRepository appUserRepository,
            AppUserService appUserService,
            ConfirmationTokenRepository confirmationTokenRepository,
            EmailService emailService,
            ConfirmationTokenService confirmationTokenService,
            Environment environment

    ) {
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
        this.confirmationTokenService = confirmationTokenService;
        this.environment = environment;
    }

    public String register(RegistrationRequest request) throws IllegalStateException{

        validateCredentials(request);

        AppUser newUser = appUserService.createUser(request, AppUserRole.USER);

        appUserRepository.save(newUser);

        String token = confirmationTokenService.generateConfirmationToken(newUser);

        sendConfirmationEmail(request, token);

        return token;



    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new InvalidTokenException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new InvalidTokenException("token was already submitted");
        }
        if (LocalDateTime.now().isAfter(confirmationToken.getExpiresAt())) {
            throw new InvalidTokenException("Token has expired");
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());

        AppUser appUser = appUserRepository.findByEmail(
                confirmationToken.getAppUser().getEmail()).orElseThrow(() ->
                new UserNotFoundException("The user with provided email does not exist"));

        appUser.setEnabled(true);


        return "Confirmed";
    }


    private void sendConfirmationEmail(RegistrationRequest request, String token) {
        String baseUrl = environment.getProperty("app.base-url");
        String link = baseUrl + "/api/v1/registration/confirm?token=" + token;
        emailService.send(request.getEmail(), emailService.generateConfirmationEmail(link,request.getFirstName()),"Confirm your email");
    }

    private void validateCredentials(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());

        String INVALID_PASSWORD_MESSAGE = "the password does not match requirements. " +
                "It should consist of at least 12 characters, include uppercase and lowercase characters and" +
                " contain at least one special character";

        if (!isValidEmail) {
            throw new InvalidEmailFormatException("Email is not valid");
        }

        boolean isTaken = appUserRepository.existsByEmail(request.getEmail());
        if (isTaken) {
            throw new InvalidEmailFormatException("The user with provided email already exists");
        }

        boolean isValidFormatPassword = passwordValidator.test(request.getPassword());
        if (!isValidFormatPassword) {
            throw new InvalidPasswordFormatException(INVALID_PASSWORD_MESSAGE);
        }
    }
}

