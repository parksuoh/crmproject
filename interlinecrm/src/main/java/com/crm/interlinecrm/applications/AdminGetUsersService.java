package com.crm.interlinecrm.applications;

import com.crm.interlinecrm.dtos.PagedUserDto;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional(readOnly = true)
public class AdminGetUsersService {

    private final UserRepository userRepository;

    public AdminGetUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Page<PagedUserDto> get(Pageable pageable){
        Page<User> users = userRepository.findAllByOrderByIdDesc(pageable);
        return users.map(user ->
                new PagedUserDto(
                        user.userId().toString(),
                        user.uid(),
                        user.name().toString(),
                        user.phone().toString(),
                        user.address().toString(),
                        user.role().toString()
                ));
    }
}
