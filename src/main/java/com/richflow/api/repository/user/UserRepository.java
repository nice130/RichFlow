package com.richflow.api.repository.user;

import com.richflow.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String id);

    boolean existsByUserId(String id);
}
