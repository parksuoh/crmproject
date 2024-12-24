package com.crm.interlinecrm.dtos;

import jakarta.validation.constraints.NotEmpty;

public record ChangeCustomerCompanyRequestDto(
        @NotEmpty
        String customerCompanyId,
        @NotEmpty
        String companyName,
        String boss,
        String phone,
        String address,
        String memo
) {
}
