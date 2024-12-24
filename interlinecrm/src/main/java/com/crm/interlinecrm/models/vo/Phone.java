package com.crm.interlinecrm.models.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Phone {

    @Column(name = "phone")
    private String phone;

    private Phone(){}

    public Phone(String phone){ this.phone = phone; }

    @Override
    public String toString() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone1 = (Phone) o;
        return Objects.equals(phone, phone1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phone);
    }
}
