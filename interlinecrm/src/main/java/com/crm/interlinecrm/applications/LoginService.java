package com.crm.interlinecrm.applications;


import com.crm.interlinecrm.dtos.LoginRequestDto;
import com.crm.interlinecrm.dtos.LoginResultDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.repositories.UserRepository;
import com.crm.interlinecrm.security.AccessTokenGenerator;
import com.crm.interlinecrm.security.AuthUserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenGenerator accessTokenGenerator;
    private final AuthUserDao authUserDao;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, AccessTokenGenerator accessTokenGenerator, AuthUserDao authUserDao) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenGenerator = accessTokenGenerator;
        this.authUserDao = authUserDao;
    }


    public LoginResultDto login(LoginRequestDto loginRequestDto){
        String accessToken = null;
        boolean isAdmin = false;
        Optional<User> userOpt = userRepository.findByUid(loginRequestDto.uid());

        if(userOpt.isEmpty()){ return new LoginResultDto(accessToken, isAdmin, "uid not found"); }

        User user = userOpt.get();

        boolean matches = passwordEncoder.matches(loginRequestDto.password(), user.password());
        if(!matches){ return new LoginResultDto(accessToken, isAdmin, "password not correct"); }

        accessToken = accessTokenGenerator.generate(user.userId().toString());

        authUserDao.insertAccessToken(user.userId().toString(), accessToken);

        if (user.isAdmin()){ isAdmin = user.isAdmin(); }

        return new LoginResultDto(accessToken, isAdmin, "ok");

    }


}
