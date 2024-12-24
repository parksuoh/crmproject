package com.crm.interlinecrm.security;

import com.crm.interlinecrm.models.vo.Role;

public record AuthUser(
        String userId,
        String uid,
        String password,
        String role,
        String accessToken
) {
    public static AuthUser of(
            String userId, String uid, String password, String role) {
        return new AuthUser(userId, uid, password, role, "");
    }

    public static AuthUser authenticated(
            String userId, String role, String accessToken) {
        return new AuthUser(userId, "", "", role, accessToken);
    }

    public boolean isAdmin() {
        return Role.valueOf(role).equals(Role.ROLE_ADMIN);
    }

}