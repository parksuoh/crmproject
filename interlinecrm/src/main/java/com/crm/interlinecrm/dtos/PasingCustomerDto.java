package com.crm.interlinecrm.dtos;

public record PasingCustomerDto(
        String id,
        String companyId,
        String name,
        String phone,
        String address,
        String title,
        String memo
) {
}
