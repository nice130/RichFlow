package com.richflow.api.repository.user;

import com.richflow.api.domain.user.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, String> {

}
