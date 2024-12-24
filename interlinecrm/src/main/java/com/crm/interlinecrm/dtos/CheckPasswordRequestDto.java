package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CheckPasswordRequestDto(
        @NotEmpty
        String password
) {
}
