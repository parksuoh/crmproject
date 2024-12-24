package com.crm.interlinecrm.models.vo;

public class UserId extends EntityId {
    private UserId() {
        super();
    }

    public UserId(String value) {
        super(value);
    }

    public static UserId generate() {
        return new UserId(newTsid());
    }

}