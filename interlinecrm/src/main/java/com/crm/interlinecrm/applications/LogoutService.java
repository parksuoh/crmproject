package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.security.AuthUserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutService {

    private final AuthUserDao authUserDao;

    public LogoutService(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }


    public BasicStateResultDto logout(String accessToken){

        authUserDao.deleteAccessToken(accessToken);

        return new BasicStateResultDto("ok");
    }
}
