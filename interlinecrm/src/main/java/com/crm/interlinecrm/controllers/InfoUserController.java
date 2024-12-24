package com.crm.interlinecrm.controllers;


import com.crm.interlinecrm.applications.ChangePasswordService;
import com.crm.interlinecrm.applications.ChangeUserInfoService;
import com.crm.interlinecrm.applications.CheckPasswordService;
import com.crm.interlinecrm.applications.GetUserInfo;
import com.crm.interlinecrm.dtos.*;
import com.crm.interlinecrm.security.AuthUser;
import com.crm.interlinecrm.security.NormalRequired;
import jakarta.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@NormalRequired
@RestController
@RequestMapping("/api/info-user")
public class InfoUserController {

    private final GetUserInfo getUserInfo;
    private final ChangeUserInfoService changeUserInfoService;
    private final CheckPasswordService checkPasswordService;
    private final ChangePasswordService changePasswordService;

    public InfoUserController(GetUserInfo getUserInfo, ChangeUserInfoService changeUserInfoService, CheckPasswordService checkPasswordService, ChangePasswordService changePasswordService) {
        this.getUserInfo = getUserInfo;
        this.changeUserInfoService = changeUserInfoService;
        this.checkPasswordService = checkPasswordService;
        this.changePasswordService = changePasswordService;
    }


    @GetMapping
    public UserInfoResponseDto get(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){
        AuthUser authUser = (AuthUser) usernamePasswordAuthenticationToken.getPrincipal();
        return getUserInfo.get(authUser.userId());
    }

    @PatchMapping("/basic")
    public BasicStateResultDto changeBasicInfo(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
                                               @Valid @RequestBody ChangeUserInfoRequestDto changeUserInfoRequestDto){
        AuthUser authUser = (AuthUser) usernamePasswordAuthenticationToken.getPrincipal();
        return changeUserInfoService.change(
                authUser.userId(),
                changeUserInfoRequestDto.name(),
                changeUserInfoRequestDto.phone(),
                changeUserInfoRequestDto.address()
        );
    }

    @PostMapping("/password")
    public BasicBooleanResultDto checkPassword(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
                              @Valid @RequestBody CheckPasswordRequestDto checkPasswordRequestDto){
        AuthUser authUser = (AuthUser) usernamePasswordAuthenticationToken.getPrincipal();
        return checkPasswordService.check(authUser.userId(), checkPasswordRequestDto.password());
    }

    @PatchMapping("/password")
    public BasicStateResultDto changePassword(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken,
                               @Valid @RequestBody ChangePasswordRequestDto changePasswordRequestDto){
        AuthUser authUser = (AuthUser) usernamePasswordAuthenticationToken.getPrincipal();
        return changePasswordService.change(authUser.userId(), changePasswordRequestDto.password());
    }

}
