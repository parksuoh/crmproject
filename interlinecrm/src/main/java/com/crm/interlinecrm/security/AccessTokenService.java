package com.crm.interlinecrm.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AccessTokenService {
    private final AccessTokenGenerator accessTokenGenerator;
    private final AuthUserDao authUserDao;

    public AccessTokenService(AccessTokenGenerator accessTokenGenerator,
                              AuthUserDao authUserDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.authUserDao = authUserDao;
    }


    public UsernamePasswordAuthenticationToken authenticate(String accessToken) {
        if (!accessTokenGenerator.verify(accessToken)) {
            return null;
        }

        AuthUser authUser = authUserDao.findByAccessToken(accessToken);
        return UsernamePasswordAuthenticationToken.authenticated(authUser, null, List.of(authUser::role));

    }
}