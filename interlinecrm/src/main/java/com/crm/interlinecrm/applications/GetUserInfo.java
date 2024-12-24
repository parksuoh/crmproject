package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.UserInfoResponseDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.models.vo.UserId;
import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GetUserInfo {

    private final UserRepository userRepository;

    public GetUserInfo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfoResponseDto get(String userId){

        User user = userRepository.findById(new UserId(userId)).orElseThrow();

        return new UserInfoResponseDto(
                user.name().toString(),
                user.phone().toString(),
                user.address().toString()
        );
    }


}
