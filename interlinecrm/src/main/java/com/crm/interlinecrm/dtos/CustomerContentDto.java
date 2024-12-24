package com.crm.interlinecrm.dtos;

public record CustomerContentDto(
        String id,
        String customerName,
        String phone,
        String address,
        String title,
        String memo,
        String companyId,
        String companyName
) {
}
