package com.richflow.api.repository;

import com.richflow.api.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String id);

    boolean existsByUserId(String id);
}
