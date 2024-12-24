package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.AuthResponseDto;
import com.crm.interlinecrm.security.AccessTokenGenerator;
import com.crm.interlinecrm.security.AuthUser;
import com.crm.interlinecrm.security.AuthUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final AccessTokenGenerator accessTokenGenerator;
    private final AuthUserDao authUserDao;

    public AuthService(AccessTokenGenerator accessTokenGenerator, AuthUserDao authUserDao) {
        this.accessTokenGenerator = accessTokenGenerator;
        this.authUserDao = authUserDao;
    }

    public AuthResponseDto auth(AuthUser authUser){

        String newAccessToken = accessTokenGenerator.generate(authUser.userId());
        authUserDao.deleteAccessToken(authUser.accessToken());

        return new AuthResponseDto(newAccessToken);
    }
}
