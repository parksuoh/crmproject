package com.crm.interlinecrm.applications;


import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.ChangeUserInfoRequestDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.models.vo.Address;
import com.crm.interlinecrm.models.vo.Name;
import com.crm.interlinecrm.models.vo.Phone;
import com.crm.interlinecrm.models.vo.UserId;

import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangeUserInfoService {

    private final UserRepository userRepository;

    public ChangeUserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public BasicStateResultDto change(String userId, String name, String phone, String address){

        User user = userRepository.findById(new UserId(userId)).orElseThrow();
        user.changeBasicInfo(
                new Name(name),
                new Phone(phone),
                new Address(address)
        );

        userRepository.save(user);

        return new BasicStateResultDto("ok");
    }
}
