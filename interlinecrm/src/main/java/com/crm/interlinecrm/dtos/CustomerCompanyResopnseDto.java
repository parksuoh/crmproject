package com.crm.interlinecrm.dtos;

public record CustomerCompanyResopnseDto(
        String companyId,
        String companyName,
        String boss,
        String phone,
        String address,
        String memo
) {
}
