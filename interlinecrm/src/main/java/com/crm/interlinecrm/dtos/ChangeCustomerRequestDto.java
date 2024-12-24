package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record ChangeCustomerRequestDto(
        @NotEmpty
        String customerCompanyId,
        @NotEmpty
        String customerId,
        @NotEmpty
        String name,
        String phone,
        String address,
        String title,
        String memo
) {
}
