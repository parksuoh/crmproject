package com.crm.interlinecrm.applications;


import com.crm.interlinecrm.dtos.PagedCustomerCompanyDto;
import com.crm.interlinecrm.models.entity.CustomerCompany;
import com.crm.interlinecrm.repositories.CustomerCompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetCustomerCompanysService {
    
    private final CustomerCompanyRepository customerCompanyRepository;

    public GetCustomerCompanysService(CustomerCompanyRepository customerCompanyRepository) {
        this.customerCompanyRepository = customerCompanyRepository;
    }

    public Page<PagedCustomerCompanyDto> get(Pageable pageable){
        Page<CustomerCompany> customerCompanys = customerCompanyRepository.findAllByOrderByIdDesc(pageable);

        return customerCompanys.map(company -> new PagedCustomerCompanyDto(
                company.customerCompanyId().toString(),
                company.companyName(),
                company.boss().toString(),
                company.phone().toString(),
                company.address().toString(),
                company.memo()
        ));
    }
}
