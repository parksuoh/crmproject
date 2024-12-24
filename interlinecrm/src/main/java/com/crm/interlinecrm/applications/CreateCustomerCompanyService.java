package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.CreateCustomerCompanyRequestDto;
import com.crm.interlinecrm.models.entity.CustomerCompany;
import com.crm.interlinecrm.models.vo.Address;
import com.crm.interlinecrm.models.vo.CustomerCompanyId;
import com.crm.interlinecrm.models.vo.Name;
import com.crm.interlinecrm.models.vo.Phone;
import com.crm.interlinecrm.repositories.CustomerCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateCustomerCompanyService {

    private final CustomerCompanyRepository customerCompanyRepository;

    public CreateCustomerCompanyService(CustomerCompanyRepository customerCompanyRepository) {
        this.customerCompanyRepository = customerCompanyRepository;
    }


    public BasicStateResultDto create(CreateCustomerCompanyRequestDto createCustomerCompanyRequestDto){
        CustomerCompany customerCompany = new CustomerCompany(
                CustomerCompanyId.generate(),
                createCustomerCompanyRequestDto.companyName(),
                new Name(createCustomerCompanyRequestDto.boss()),
                new Phone(createCustomerCompanyRequestDto.phone()),
                new Address(createCustomerCompanyRequestDto.address()),
                createCustomerCompanyRequestDto.memo()
        );

        customerCompanyRepository.save(customerCompany);

        return new BasicStateResultDto("ok");

    }
}
