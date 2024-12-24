package com.crm.interlinecrm.controllers;

import com.crm.interlinecrm.applications.ChangeCustomerService;
import com.crm.interlinecrm.applications.CreateCustomerService;
import com.crm.interlinecrm.applications.GetCustomersService;
import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.ChangeCustomerRequestDto;
import com.crm.interlinecrm.dtos.CreateCustomerRequestDto;
import com.crm.interlinecrm.dtos.GetCustomersResponseDto;
import com.crm.interlinecrm.security.NormalRequired;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@NormalRequired
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;
    private final ChangeCustomerService changeCustomerService;
    private final GetCustomersService getCustomersService;

    public CustomerController(CreateCustomerService createCustomerService, ChangeCustomerService changeCustomerService, GetCustomersService getCustomersService) {
        this.createCustomerService = createCustomerService;
        this.changeCustomerService = changeCustomerService;
        this.getCustomersService = getCustomersService;
    }

    @GetMapping
    public GetCustomersResponseDto getCustomers(@RequestParam("page") int page, @RequestParam("size") int size){
        return getCustomersService.get(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BasicStateResultDto create(@Valid @RequestBody CreateCustomerRequestDto createCustomerRequestDto){

        return createCustomerService.create(createCustomerRequestDto);

    }

    @PatchMapping
    public BasicStateResultDto changeCustomer(@Valid @RequestBody ChangeCustomerRequestDto changeCustomerRequestDto){
        return changeCustomerService.change(changeCustomerRequestDto);
    }
}
