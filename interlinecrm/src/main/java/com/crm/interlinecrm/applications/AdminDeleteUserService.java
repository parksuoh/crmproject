package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.BasicStateResultDto;
import com.crm.interlinecrm.dtos.DeleteUserRequestDto;
import com.crm.interlinecrm.models.vo.UserId;
import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminDeleteUserService {

    private final UserRepository userRepository;

    public AdminDeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public BasicStateResultDto delete(DeleteUserRequestDto deleteUserRequestDto){
        userRepository.deleteById(new UserId(deleteUserRequestDto.userId()));

        return new BasicStateResultDto("ok");
    }
}
