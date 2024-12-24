package com.crm.interlinecrm.repositories.jdbc;

import com.crm.interlinecrm.dtos.TotalElementCustomerDto;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.crm.interlinecrm.dtos.PasingCustomerDto;

import java.util.List;

@Component
public class JdbcCustomerDao {

    private final JdbcClient jdbcClient;

    public JdbcCustomerDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<PasingCustomerDto> findPagedCustomer(int page, int size){
        int offsetSize = page * size;

        String query = """
                select id, company_id, name, phone, address, title, memo
                  from customer
                 order by id desc
                 limit :size offset :offsetSize;
                """;

        return jdbcClient.sql(query)
                .param("size", size)
                .param("offsetSize", offsetSize)
                .query(PasingCustomerDto.class)
                .list();
    }

    public TotalElementCustomerDto findTotalElementCustomer(){
        String query = """
               select COUNT(*) as total_element
                 from customer ;
                """;

        return jdbcClient.sql(query)
                .query(TotalElementCustomerDto.class)
                .single();
    }



}
