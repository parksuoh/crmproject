package com.crm.interlinecrm.repositories;

import com.crm.interlinecrm.models.entity.CustomerCompany;
import com.crm.interlinecrm.models.vo.CustomerCompanyId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCompanyRepository extends JpaRepository<CustomerCompany, CustomerCompanyId> {

    Page<CustomerCompany> findAllByOrderByIdDesc(Pageable pageable);

}
