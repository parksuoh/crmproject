package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record AdminChangePasswordRequestDto(
        @NotEmpty
        String userId,
        @NotEmpty
        String password
) {
}
