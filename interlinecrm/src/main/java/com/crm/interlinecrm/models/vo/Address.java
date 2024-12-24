package com.crm.interlinecrm.models.vo;

import jakarta.persistence.Column;

import java.util.Objects;

public class Address {

    @Column(name = "address")
    private String address;

    private Address(){}

    public Address(String address){ this.address = address; }


    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(address);
    }
}
