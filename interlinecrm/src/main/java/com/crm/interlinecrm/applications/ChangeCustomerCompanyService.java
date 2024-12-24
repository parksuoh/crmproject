package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.ChangeCustomerCompanyRequestDto;
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
public class ChangeCustomerCompanyService {

    private final CustomerCompanyRepository customerCompanyRepository;

    public ChangeCustomerCompanyService(CustomerCompanyRepository customerCompanyRepository) {
        this.customerCompanyRepository = customerCompanyRepository;
    }

    public BasicStateResultDto change(ChangeCustomerCompanyRequestDto changeCustomerCompanyRequestDto){
        CustomerCompanyId customerCompanyId = new CustomerCompanyId(changeCustomerCompanyRequestDto.customerCompanyId());
        CustomerCompany customerCompany = customerCompanyRepository.findById(customerCompanyId).orElseThrow();

        customerCompany.changeCompanyInfo(
                changeCustomerCompanyRequestDto.companyName(),
                new Name(changeCustomerCompanyRequestDto.boss()),
                new Phone(changeCustomerCompanyRequestDto.phone()),
                new Address(changeCustomerCompanyRequestDto.address()),
                changeCustomerCompanyRequestDto.memo()
        );

        customerCompanyRepository.save(customerCompany);

        return new BasicStateResultDto("ok");
    }
}
