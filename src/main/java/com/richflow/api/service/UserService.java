package com.richflow.api.service;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.User;
import com.richflow.api.repository.UserRepository;
import com.richflow.api.request.UserLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserLogin userLogin) throws BadRequestException {
        User user = new User();
        if(userRepository.existsByUserId(userLogin.getUserId())) {
            throw new BadRequestException("이미 사용중인 아이디입니다.");
        } else {
            user.setUserId(userLogin.getUserId());
            user.setUserPassword(userLogin.getUserPassword());
            user.setJoinType(userLogin.getJoinType());
            user.setUserStatus("Y");
            user.setUserNickname(userLogin.getUserNickname());
            userRepository.save(user);
            return user;
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Long getIdxByUserId(final String id) {
        return userRepository.findByUserIdx(id);
    }

    public boolean getByCredentials(final String id, final String password) {
        return getPasswordByUserId(id).equals(password);
    }

    public String getPasswordByUserId(String id) {
        User user = userRepository.findByUserId(id);
        return user.getUserPassword();
    }

}
