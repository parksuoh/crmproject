package com.crm.interlinecrm.models.entity;

import com.crm.interlinecrm.models.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerCompanyTest {

    private CustomerCompany testCompany;

    @BeforeEach
    void setUp() {
        testCompany = new CustomerCompany(
                CustomerCompanyId.generate(),
                "testCompony",
                new Name("test boss"),
                new Phone("123"),
                new Address("add"),
                "testMemo"
        );
    }

    @Test
    @DisplayName("Test changeCompanyInfo")
    void changeCompanyInfo(){
        testCompany.changeCompanyInfo(
                "testCompony1",
                new Name("test boss1"),
                new Phone("1231"),
                new Address("add1"),
                "testMemo1"
        );
        assertThat(testCompany.companyName()).isEqualTo("testCompony1");
        assertThat(testCompany.boss().toString()).isEqualTo("test boss1");
        assertThat(testCompany.phone().toString()).isEqualTo("1231");
        assertThat(testCompany.address().toString()).isEqualTo("add1");
        assertThat(testCompany.memo()).isEqualTo("testMemo1");
    }


    @Test
    @DisplayName("Test add and find customer")
    void addCustomer(){
        testCompany.addCustomer(
                new Name("testCustomer"),
                new Phone("1122"),
                new Address("qwer"),
                "ttt",
                "memoss"
        );

        testCompany.addCustomer(
                new Name("testCustomer"),
                new Phone("1122"),
                new Address("qwer"),
                "ttt",
                "memoss"
        );

        testCompany.addCustomer(
                new Name("testCustomer"),
                new Phone("1122"),
                new Address("qwer"),
                "ttt",
                "memoss"
        );

        assertThat(testCompany.customersSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("Test findCustomer")
    void findCustomer(){
        Customer customer = testCompany.addCustomer(
                new Name("findCustomer"),
                new Phone("1122"),
                new Address("qwer"),
                "ttt",
                "memoss"
        );

        Customer customerTT = testCompany.findCustomer(customer.customerId()).get();

        assertThat(customer.customerId()).isEqualTo(customerTT.customerId());
    }


    @Test
    @DisplayName("Test findCustomer")
    void changeCustomer(){
        Customer customer = testCompany.addCustomer(
                new Name("chaingeCustomer"),
                new Phone("1122"),
                new Address("qwer"),
                "ttt",
                "memoss"
        );

        testCompany.changeCustomer(
                customer.customerId(),
                new Name("ss"),
                new Phone("ss"),
                new Address("ss"),
                "ss",
                "ss"
        );

        assertThat(customer.name().toString()).isEqualTo("ss");
    }



}