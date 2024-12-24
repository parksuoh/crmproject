package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record CreateCustomerRequestDto(
    @NotEmpty
    String companyId,
    @NotEmpty
    String name,
    String phone,
    String address,
    String title,
    String memo
) {
}
