package com.crm.interlinecrm.controllers;

import com.crm.interlinecrm.applications.AuthService;
import com.crm.interlinecrm.applications.LogoutService;
import com.crm.interlinecrm.dtos.AuthResponseDto;
import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.security.AuthUser;
import com.crm.interlinecrm.security.NormalRequired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NormalRequired
@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final AuthService authService;

    private final LogoutService logoutService;

    public SessionController(AuthService authService, LogoutService logoutService) {
        this.authService = authService;
        this.logoutService = logoutService;
    }


    @GetMapping
    public AuthResponseDto auth(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
        AuthUser authUser = (AuthUser) usernamePasswordAuthenticationToken.getPrincipal();
        return authService.auth(authUser);
    }

    @DeleteMapping
    public BasicStateResultDto logout(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        AuthUser authUser = (AuthUser) usernamePasswordAuthenticationToken.getPrincipal();

        return logoutService.logout(authUser.accessToken());
    }

}
