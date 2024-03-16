package com.richflow.api.service.user;

import com.richflow.api.common.CommonUtil;
import com.richflow.api.domain.user.User;
import com.richflow.api.domain.user.UserResponseCode;
import com.richflow.api.domain.user.UserLog;
import com.richflow.api.repository.user.UserLogRepository;
import com.richflow.api.repository.user.UserRepository;
import com.richflow.api.request.user.UserRequest;
import com.richflow.api.response.user.UserResponse;
import com.richflow.api.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserLogRepository userLogRepository;
    private TokenProvider tokenProvider;

    public User createUser(UserRequest userRequest) {
        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setUserPassword(userRequest.getUserPassword());
        user.setUserJoinType(userRequest.getUserJoinType());
        user.setUserStatus("Y");
        String nickname = "";
        if(Objects.isNull(userRequest.getUserNickname())) {
            nickname = userRequest.getUserId();
            int idx = nickname.indexOf("@");
            nickname = nickname.substring(0, idx);
        } else {
            nickname = userRequest.getUserNickname();
        }
        user.setUserNickname(userRequest.getUserNickname());
        userRepository.save(user);
        return user;
    }

    public boolean getExistsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
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

    public void setUserLog(UserRequest userRequest) {
        UserLog userLog = new UserLog();
        userLog.setUserIdx(userRequest.getUserIdx());
        userLog.setUslgUpdateIp(userRequest.getUslgUpdateIp());
        userLog.setUslgUpdateAt(CommonUtil.getTimestamp());
        userLogRepository.save(userLog);
    }

    public static UserResponse buildUserResponse(int code, Map<String, Object> data) {
        UserResponse userResponse = UserResponse.builder()
                .code(code)
                .message(UserResponseCode.getMessage(code))
                .data(data)
                .build();
        return userResponse;
    }

    public static UserResponse buildUserResponse(int code, String error) {
        UserResponse userResponse = UserResponse.builder()
                .code(code)
                .message(error)
                .build();
        return userResponse;
    }

    public static UserResponse buildUserResponse(int code) {
        UserResponse userResponse = UserResponse.builder()
                .code(code)
                .message(UserResponseCode.getMessage(code))
                .build();
        return userResponse;
    }
}
