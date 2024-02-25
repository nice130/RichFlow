package com.richflow.api.service;

import com.richflow.api.domain.User.User;
import com.richflow.api.domain.User.UserCode;
import com.richflow.api.repository.UserRepository;
import com.richflow.api.request.UserLogin;
import com.richflow.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
            user.setUserJoinType(userLogin.getUserJoinType());
            user.setUserStatus("Y");
            user.setUserNickname(userLogin.getUserNickname());
            userRepository.save(user);
            return user;
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Long getUserIdxByUserId(final String id) {
        User user = userRepository.findByUserId(id);
        return user.getUserIdx();
    }

    public boolean getByCredentials(final String id, final String password) {
        return getPasswordByUserId(id).equals(password);
    }

    public String getPasswordByUserId(final String id) {
        User user = userRepository.findByUserId(id);
        return user.getUserPassword();
    }

    public String getUserNicknameByUserId(final String userId) {
        User user = userRepository.findByUserId(userId);
        return user.getUserNickname();
    }

    public static UserResponse buildUserResponse(int code, Map<String, Object> data) {
        UserResponse userResponse = UserResponse.builder()
                .code(code)
                .message(UserCode.getMessage(code))
                .data(data)
                .build();
        return userResponse;
    }

    public static UserResponse buildUserResponse(int code) {
        UserResponse userResponse = UserResponse.builder()
                .code(code)
                .message(UserCode.getMessage(code))
                .build();
        return userResponse;
    }
}
