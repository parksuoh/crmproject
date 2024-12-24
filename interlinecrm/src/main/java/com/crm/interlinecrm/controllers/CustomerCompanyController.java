package com.crm.interlinecrm.controllers;

import com.crm.interlinecrm.applications.ChangeCustomerCompanyService;
import com.crm.interlinecrm.applications.CreateCustomerCompanyService;
import com.crm.interlinecrm.applications.GetCustomerCompanyService;
import com.crm.interlinecrm.applications.GetCustomerCompanysService;
import com.crm.interlinecrm.dtos.*;
import com.crm.interlinecrm.security.NormalRequired;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@NormalRequired
@RestController
@RequestMapping("/api/customer-company")
public class CustomerCompanyController {

    private final CreateCustomerCompanyService createCustomerCompanyService;
    private final ChangeCustomerCompanyService changeCustomerCompanyService;
    private final GetCustomerCompanysService getCustomerCompanysService;
    private final GetCustomerCompanyService getCustomerCompanyService;

    public CustomerCompanyController(CreateCustomerCompanyService createCustomerCompanyService, ChangeCustomerCompanyService changeCustomerCompanyService, GetCustomerCompanysService getCustomerCompanysService, GetCustomerCompanyService getCustomerCompanyService) {
        this.createCustomerCompanyService = createCustomerCompanyService;
        this.changeCustomerCompanyService = changeCustomerCompanyService;
        this.getCustomerCompanysService = getCustomerCompanysService;
        this.getCustomerCompanyService = getCustomerCompanyService;
    }

    @GetMapping
    public Page<PagedCustomerCompanyDto> getCustomerCompanys(Pageable pageable) {
        return getCustomerCompanysService.get(pageable);
    }

    @GetMapping("/detail/{companyId}")
    public CustomerCompanyResopnseDto getCustomerCompany(@PathVariable String companyId) {
        return getCustomerCompanyService.get(companyId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicStateResultDto createCustomerCompany(@Valid @RequestBody CreateCustomerCompanyRequestDto createCustomerCompanyRequestDto){
        return createCustomerCompanyService.create(createCustomerCompanyRequestDto);
    }

    @PatchMapping
    public BasicStateResultDto changeCustomerCompany(@Valid @RequestBody ChangeCustomerCompanyRequestDto changeCustomerCompanyRequestDto){
        return changeCustomerCompanyService.change(changeCustomerCompanyRequestDto);
    }

}
