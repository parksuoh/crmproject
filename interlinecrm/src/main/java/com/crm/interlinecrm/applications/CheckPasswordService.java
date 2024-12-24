package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicBooleanResultDto;
import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.models.vo.UserId;
import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CheckPasswordService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public CheckPasswordService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public BasicBooleanResultDto check(String userId, String password) {
        User user = userRepository.findById(new UserId(userId)).orElseThrow();

        boolean isPasswordOk = user.checkPassword(password, passwordEncoder);

        if(!isPasswordOk){
            return new BasicBooleanResultDto(false);
        }
        return new BasicBooleanResultDto(true);
    }
}
