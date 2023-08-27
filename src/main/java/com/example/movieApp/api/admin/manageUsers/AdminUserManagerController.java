package com.example.movieApp.api.admin.manageUsers;

import com.example.movieApp.appUser.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin/userManager")
public class AdminUserManagerController {
    private final AdminUserManagerService adminUserManagerService;

    @Autowired
    public AdminUserManagerController(AdminUserManagerService adminUserManagerService) {
        this.adminUserManagerService = adminUserManagerService;
    }

    @GetMapping(path = "/getAllUsers")
    public List<AppUser> getAllUsers(){
        return adminUserManagerService.getAllUsers();
    }

    @PutMapping(path = "/giveAdminRights/{appUserId}")
    public AppUser giveAdminRights(@PathVariable Long appUserId){
        return adminUserManagerService.giveAdminRights(appUserId);
    }

}
