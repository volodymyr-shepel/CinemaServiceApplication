package com.example.movieApp.api.admin.manageUsers;

import com.example.movieApp.appUser.AppUser;
import com.example.movieApp.appUser.AppUserRepository;
import com.example.movieApp.appUser.AppUserRole;
import com.example.movieApp.excepitons.InstanceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserManagerService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AdminUserManagerService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    // Used to give admin rights to another user
    @Transactional
    public AppUser giveAdminRights(Long appUserId) {
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(() -> new InstanceNotFoundException("There is no user with provided id"));
        appUser.setAppUserRole(AppUserRole.ADMIN);
        return appUser;
    }

}
