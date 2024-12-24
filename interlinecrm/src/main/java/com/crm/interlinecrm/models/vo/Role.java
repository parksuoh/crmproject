package com.crm.interlinecrm.models.vo;

public enum Role {
    ROLE_NORMAL("ROLE_NORMAL"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }


}