package com.crm.interlinecrm.dtos;

import java.util.List;

public record GetCustomersResponseDto(
        List<CustomerContentDto> content,
        boolean last,
        int totalPages,
        int totalElement,
        int size
) {
}
