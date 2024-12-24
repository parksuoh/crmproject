package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record ChangePasswordRequestDto(
        @NotEmpty
        String password
) {
}
