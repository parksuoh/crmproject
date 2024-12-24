package com.crm.interlinecrm.dtos;

public record PagedUserDto (
        String userId,
        String uid,
        String name,
        String phone,
        String address,
        String role
) {
}
