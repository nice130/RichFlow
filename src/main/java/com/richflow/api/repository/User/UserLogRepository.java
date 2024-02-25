package com.richflow.api.repository.User;

import com.richflow.api.domain.User.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, String> {

}
