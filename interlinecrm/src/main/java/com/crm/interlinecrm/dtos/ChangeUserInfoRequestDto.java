package com.crm.interlinecrm.dtos;

public record ChangeUserInfoRequestDto(
        String name,
        String phone,
        String address
) {
}
