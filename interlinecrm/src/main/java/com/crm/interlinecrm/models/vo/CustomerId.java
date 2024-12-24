package com.crm.interlinecrm.models.vo;

public class CustomerId extends EntityId {
    private CustomerId() {
        super();
    }

    public CustomerId(String value) {
        super(value);
    }

    public static CustomerId generate() {
        return new CustomerId(newTsid());
    }

}