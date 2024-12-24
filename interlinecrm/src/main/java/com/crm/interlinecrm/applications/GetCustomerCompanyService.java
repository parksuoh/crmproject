package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.CustomerCompanyResopnseDto;
import com.crm.interlinecrm.models.entity.CustomerCompany;
import com.crm.interlinecrm.models.vo.CustomerCompanyId;
import com.crm.interlinecrm.repositories.CustomerCompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetCustomerCompanyService {

    private final CustomerCompanyRepository customerCompanyRepository;

    public GetCustomerCompanyService(CustomerCompanyRepository customerCompanyRepository) {
        this.customerCompanyRepository = customerCompanyRepository;
    }

    public CustomerCompanyResopnseDto get(String companyId){
        CustomerCompany customerCompany = customerCompanyRepository.findById(new CustomerCompanyId(companyId)).orElseThrow();
        return new CustomerCompanyResopnseDto(
                customerCompany.customerCompanyId().toString(),
                customerCompany.companyName().toString(),
                customerCompany.boss().toString(),
                customerCompany.phone().toString(),
                customerCompany.address().toString(),
                customerCompany.memo()
        );
    }
}
