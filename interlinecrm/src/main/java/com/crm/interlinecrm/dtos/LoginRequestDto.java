package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDto(
        @NotEmpty
        String uid,
        @NotEmpty
        String password
) {
}