package com.crm.interlinecrm.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.crm.interlinecrm.models.entity.User;
import com.crm.interlinecrm.models.vo.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {

    Optional<User> findByUid(String uid);

    boolean existsByUid(String uid);

    Page<User> findAllByOrderByIdDesc(Pageable pageable);
}
