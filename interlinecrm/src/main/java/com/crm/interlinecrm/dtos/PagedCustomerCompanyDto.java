package com.crm.interlinecrm.dtos;

public record PagedCustomerCompanyDto(
        String companyId,
        String companyName,
        String boss,
        String phone,
        String address,
        String memo
) {
}
