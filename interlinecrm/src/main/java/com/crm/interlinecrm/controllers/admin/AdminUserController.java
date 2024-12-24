package com.crm.interlinecrm.controllers.admin;

import com.crm.interlinecrm.applications.*;
import com.crm.interlinecrm.dtos.*;
import com.crm.interlinecrm.security.AdminRequired;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AdminRequired
@RestController
@RequestMapping("/api/admin-user")
public class AdminUserController {

    private final AdminCreateUserService adminCreateUserService;
    private final AdminDeleteUserService adminDeleteUserService;
    private final ChangeUserInfoService changeUserInfoService;
    private final CheckPasswordService checkPasswordService;
    private final ChangePasswordService changePasswordService;
    private final AdminGetUsersService adminGetUsersService;

    public AdminUserController(AdminCreateUserService adminCreateUserService, AdminDeleteUserService adminDeleteUserService, ChangeUserInfoService changeUserInfoService, CheckPasswordService checkPasswordService, ChangePasswordService changePasswordService, AdminGetUsersService adminGetUsersService) {
        this.adminCreateUserService = adminCreateUserService;
        this.adminDeleteUserService = adminDeleteUserService;
        this.changeUserInfoService = changeUserInfoService;
        this.checkPasswordService = checkPasswordService;
        this.changePasswordService = changePasswordService;
        this.adminGetUsersService = adminGetUsersService;
    }

    @GetMapping
    public Page<PagedUserDto> getUsers(Pageable pageable) {
        return adminGetUsersService.get(pageable);
    }

    @PostMapping("/password")
    public BasicBooleanResultDto checkPassword(@Valid @RequestBody AdminCheckPasswordRequestDto adminCheckPasswordRequestDto){
        return checkPasswordService.check(adminCheckPasswordRequestDto.userId(), adminCheckPasswordRequestDto.password());
    }

    @PatchMapping("/password")
    public BasicStateResultDto changePassword(@Valid @RequestBody AdminChangePasswordRequestDto adminChangePasswordRequestDto){
        return changePasswordService.change(adminChangePasswordRequestDto.userId(), adminChangePasswordRequestDto.password());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicStateResultDto createUser(@Valid @RequestBody CreateUserRequstDto createUserRequstDto){
        return adminCreateUserService.create(createUserRequstDto);
    }

    @DeleteMapping
    public BasicStateResultDto deleteUser(@RequestBody DeleteUserRequestDto deleteUserRequestDto) {
        return adminDeleteUserService.delete(deleteUserRequestDto);
    }

    @PatchMapping("/basic")
    public BasicStateResultDto changeBasicInfo(@Valid @RequestBody AdminChangeUserInfoRequestDto adminChangeUserInfoRequestDto){
        return changeUserInfoService.change(
                adminChangeUserInfoRequestDto.userId(),
                adminChangeUserInfoRequestDto.name(),
                adminChangeUserInfoRequestDto.phone(),
                adminChangeUserInfoRequestDto.address());
    }



}
