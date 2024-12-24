package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record AdminCheckPasswordRequestDto(
        @NotEmpty
        String userId,
        @NotEmpty
        String password
) {
}
