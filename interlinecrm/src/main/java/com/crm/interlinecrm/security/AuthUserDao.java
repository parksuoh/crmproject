package com.crm.interlinecrm.security;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserDao {

    private final JdbcClient jdbcClient;
    public AuthUserDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void insertAccessToken(String userId, String accessToken){
        String query = """
                INSERT INTO access_tokens (token , user_id) VALUES (:token, :userid) ;
                """;
        jdbcClient.sql(query)
                .param("userid", userId)
                .param("token", accessToken)
                .update();
    }

    public AuthUser findByAccessToken(String accessToken) {
        String query = """
                SELECT u.id as userId, u.uid, u.password, u.role, a.token as access_token
                  FROM user u
                  JOIN access_tokens a
                    ON a.user_id=u.id
                 WHERE a.token=:token;
                """;
        return jdbcClient.sql(query)
                .param("token", accessToken)
                .query(AuthUser.class)
                .single();
    }

    public void deleteAccessToken(String accessToken) {
        String query = """
                DELETE FROM access_tokens WHERE token=:token ;
                """;
        jdbcClient.sql(query)
                .param("token", accessToken)
                .update();
    }

}
