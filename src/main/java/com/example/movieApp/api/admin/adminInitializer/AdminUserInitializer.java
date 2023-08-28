package com.example.movieApp.api.admin.adminInitializer;

import com.example.movieApp.entities.appUser.AppUser;
import com.example.movieApp.entities.appUser.AppUserRepository;
import com.example.movieApp.entities.appUser.AppUserRole;
import com.example.movieApp.entities.appUser.AppUserService;
import com.example.movieApp.registration.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;

    private final AppUserService appUserService;




    @Autowired
    public AdminUserInitializer(AppUserRepository appUserRepository, AppUserService appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!appUserRepository.existsByAppUserRole(AppUserRole.ADMIN)) {
            RegistrationRequest adminRequest = new RegistrationRequest("admin","password");
            AppUser adminUser = appUserService.createUser(adminRequest,AppUserRole.ADMIN);
            adminUser.setEnabled(true);
            appUserRepository.save(adminUser);
        }
    }
}