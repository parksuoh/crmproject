package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequstDto(
        @NotEmpty
        String uid,
        @NotEmpty
        String password,
        @NotEmpty
        String name,
        String phone,
        String address
) {
}
