package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.ChangeCustomerRequestDto;
import com.crm.interlinecrm.models.entity.CustomerCompany;
import com.crm.interlinecrm.models.vo.*;
import com.crm.interlinecrm.repositories.CustomerCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangeCustomerService {

    private final CustomerCompanyRepository customerCompanyRepository;

    public ChangeCustomerService(CustomerCompanyRepository customerCompanyRepository) {
        this.customerCompanyRepository = customerCompanyRepository;
    }

    public BasicStateResultDto change(ChangeCustomerRequestDto changeCustomerRequestDto){
        CustomerCompany customerCompany = customerCompanyRepository.findById(new CustomerCompanyId(changeCustomerRequestDto.customerCompanyId())).orElseThrow();
        customerCompany.changeCustomer(
                new CustomerId(changeCustomerRequestDto.customerId()),
                new Name(changeCustomerRequestDto.name()),
                new Phone(changeCustomerRequestDto.phone()),
                new Address(changeCustomerRequestDto.address()),
                changeCustomerRequestDto.title(),
                changeCustomerRequestDto.memo()
        );
        customerCompanyRepository.save(customerCompany);

        return new BasicStateResultDto("ok");
    }

}
