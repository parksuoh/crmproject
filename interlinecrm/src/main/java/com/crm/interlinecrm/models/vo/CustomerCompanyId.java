package com.crm.interlinecrm.models.vo;

public class CustomerCompanyId extends EntityId {
    private CustomerCompanyId() {
        super();
    }

    public CustomerCompanyId(String value) {
        super(value);
    }

    public static CustomerCompanyId generate() {
        return new CustomerCompanyId(newTsid());
    }

}