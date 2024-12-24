package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.CreateCustomerRequestDto;
import com.crm.interlinecrm.models.entity.Customer;
import com.crm.interlinecrm.models.entity.CustomerCompany;
import com.crm.interlinecrm.models.vo.*;
import com.crm.interlinecrm.repositories.CustomerCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateCustomerService {

    private final CustomerCompanyRepository customerCompanyRepository;

    public CreateCustomerService(CustomerCompanyRepository customerCompanyRepository) {
        this.customerCompanyRepository = customerCompanyRepository;
    }


    public BasicStateResultDto create(CreateCustomerRequestDto createCustomerRequestDto){

        CustomerCompany customerCompany = customerCompanyRepository
                .findById(new CustomerCompanyId(createCustomerRequestDto.companyId()))
                .orElseThrow();

        customerCompany.addCustomer(
                new Name(createCustomerRequestDto.name()),
                new Phone(createCustomerRequestDto.phone()),
                new Address(createCustomerRequestDto.phone()),
                createCustomerRequestDto.title(),
                createCustomerRequestDto.memo());

        customerCompanyRepository.save(customerCompany);

        return new BasicStateResultDto("ok");
    }
}
