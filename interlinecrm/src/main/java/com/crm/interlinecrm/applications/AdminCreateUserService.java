package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.CreateUserRequstDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.models.vo.Address;
import com.crm.interlinecrm.models.vo.Name;
import com.crm.interlinecrm.models.vo.Phone;
import com.crm.interlinecrm.models.vo.UserId;
import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AdminCreateUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminCreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public BasicStateResultDto create(CreateUserRequstDto createUserRequstDto){

        if (userRepository.existsByUid(createUserRequstDto.uid())){
            return new BasicStateResultDto("uid exist");
        }

        User newUser = new User(
                UserId.generate(),
                createUserRequstDto.uid(),
                new Name(createUserRequstDto.name()),
                new Phone(createUserRequstDto.phone()),
                new Address(createUserRequstDto.address())
        );

        newUser.changePassword(createUserRequstDto.password(), passwordEncoder);
        userRepository.save(newUser);

        return new BasicStateResultDto("ok");
    }
}
