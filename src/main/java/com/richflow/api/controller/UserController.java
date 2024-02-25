package com.richflow.api.controller;

import com.richflow.api.domain.User.User;
import com.richflow.api.request.UserLogin;
import com.richflow.api.response.UserResponse;
import com.richflow.api.security.TokenProvider;
import com.richflow.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public UserResponse doLogin(@RequestBody UserLogin userLogin) {
        try {
            String userId = userLogin.getUserId();
            if (userService.getByCredentials(userId, userLogin.getUserPassword())) {
                userLogin.setUserIdx(userService.getUserIdxByUserId(userId));
                String token = tokenProvider.create(userLogin);

                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("nickname", userService.getUserNicknameByUserId(userId));

                int code = 200;
                return UserService.buildUserResponse(code, data);
            } else {
                int code = 501;
                return UserService.buildUserResponse(code);
            }
        } catch (Exception e) {
            log.info(String.valueOf(e));
            int code = 502;
            return UserService.buildUserResponse(code);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/join")
    public HashMap<String, String> join(@RequestBody UserLogin userLogin) {
        log.info("가입도전");
        HashMap<String, String> resultMap = new HashMap<>();
        try {
            User user = userService.createUser(userLogin);
            userLogin.setUserIdx(user.getUserIdx());

            // 회원 로그 입력

            // 카테고리 입력

            // 회원 자산내역 입력

            resultMap.put("token", tokenProvider.create(userLogin));
            return resultMap;
        } catch (Exception e) {
            return resultMap;
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
