package com.crm.interlinecrm.models.entity;

import com.crm.interlinecrm.models.vo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    private Customer testCus1;
    private Customer testCus2;

    @BeforeEach
    void setUp() {
        testCus1 = new Customer(
                CustomerId.generate(),
                new Name("111"),
                new Phone("111"),
                new Address("111"),
                    "111",
                "111"
        );

        testCus2 = new Customer(
                CustomerId.generate(),
                new Name("222"),
                new Phone("222"),
                new Address("222"),
                "222",
                "222"
        );
    }

    @Test
    @DisplayName("Test isSameTest")
    void isSameTest(){
        boolean beTrue = testCus1.isSame(testCus1.customerId());
        boolean beFalse = testCus1.isSame(testCus2.customerId());

        assertThat(beTrue).isTrue();
        assertThat(beFalse).isFalse();
    }




}