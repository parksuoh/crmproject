package com.crm.interlinecrm.controllers;

import com.crm.interlinecrm.applications.LoginService;
import com.crm.interlinecrm.applications.LogoutService;
import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.LoginRequestDto;
import com.crm.interlinecrm.dtos.LoginResultDto;
import com.crm.interlinecrm.security.AuthUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final LoginService loginService;

    private final PasswordEncoder passwordEncoder;

    public UserController(LoginService loginService, PasswordEncoder passwordEncoder) {
        this.loginService = loginService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping
    public void getPass(){
        String aa = passwordEncoder.encode("1111");
        System.out.println(aa);
        String aa1 = passwordEncoder.encode("1111");
        System.out.println(aa1);
        String aa2 = passwordEncoder.encode("1111");
        System.out.println(aa2);


        System.out.println(passwordEncoder.matches("1111", aa));
        System.out.println(passwordEncoder.matches("1111", aa1));
        System.out.println(passwordEncoder.matches("1111", aa2));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        return loginService.login(loginRequestDto);
    }



}
