package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record AdminChangeUserInfoRequestDto(
        @NotEmpty
        String userId,
        String name,
        String phone,
        String address
) {
}
