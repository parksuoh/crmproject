package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.models.vo.UserId;
import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangePasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public BasicStateResultDto change(String userId, String password){
        User user = userRepository.findById(new UserId(userId)).orElseThrow();
        user.changePassword(password, passwordEncoder);
        userRepository.save(user);
        return new BasicStateResultDto("ok");
    }
}
