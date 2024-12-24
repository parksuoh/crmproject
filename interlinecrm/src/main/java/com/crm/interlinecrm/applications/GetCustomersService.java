package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.CustomerContentDto;
import com.crm.interlinecrm.dtos.GetCustomersResponseDto;
import com.crm.interlinecrm.dtos.PasingCustomerDto;
import com.crm.interlinecrm.repositories.CustomerCompanyRepository;
import com.crm.interlinecrm.repositories.jdbc.JdbcCustomerDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class GetCustomersService {

    private final CustomerCompanyRepository customerCompanyRepository;

    private final JdbcCustomerDao jdbcCustomerDao;

    public GetCustomersService(CustomerCompanyRepository customerCompanyRepository, JdbcCustomerDao jdbcCustomerDao) {
        this.customerCompanyRepository = customerCompanyRepository;
        this.jdbcCustomerDao = jdbcCustomerDao;
    }


    public GetCustomersResponseDto get(int page, int size){
        if(size == 0) {throw new RuntimeException("size 0");}
        int totalElement = jdbcCustomerDao.findTotalElementCustomer().totalElement();
        int totalPages = 0;
        if (totalElement > 0) {
            totalPages = totalElement % size == 0 ? (totalElement / size) - 1 : (totalElement / size);
        }
        boolean last = totalPages <= page;

        List<CustomerContentDto> content =new ArrayList<>();
        Map<String, String> companyMap = new HashMap<>();

        customerCompanyRepository.findAll().forEach(company -> {
                    companyMap.put(company.customerCompanyId().toString(), company.companyName());
        });

        List<PasingCustomerDto> pagedCustomer = jdbcCustomerDao.findPagedCustomer(page, size);
        content = pagedCustomer.stream().map(cust ->
                new CustomerContentDto(
                        cust.id(),
                        cust.name(),
                        cust.phone(),
                        cust.address(),
                        cust.title(),
                        cust.memo(),
                        cust.companyId(),
                        companyMap.get(cust.companyId())
                )).toList();

        return new GetCustomersResponseDto(
                content,
                last,
                totalPages,
                totalElement,
                size
        );
    }

}
