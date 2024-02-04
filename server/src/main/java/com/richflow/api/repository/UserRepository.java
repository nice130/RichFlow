package com.richflow.api.repository;

import com.richflow.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByMemberId(String id);

    boolean existsByMemberId(String id);
}
