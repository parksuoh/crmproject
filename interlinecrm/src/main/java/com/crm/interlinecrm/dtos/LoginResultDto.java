package com.crm.interlinecrm.dtos;

public record LoginResultDto(
        String accessToken,
        boolean isAdmin,
        String state
) {
}