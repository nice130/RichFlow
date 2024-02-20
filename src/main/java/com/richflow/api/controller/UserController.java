package com.richflow.api.controller;

import com.richflow.api.domain.User;
import com.richflow.api.request.UserLogin;
import com.richflow.api.security.TokenProvider;
import com.richflow.api.service.UserService;
import jakarta.servlet.http.HttpSession;
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
    public String doLogin(@RequestBody UserLogin userLogin) {
        try {
            log.info("login");
            String userId = userLogin.getUserId();
            if (userService.getByCredentials(userId, userLogin.getUserPassword())) {
                userLogin.setUserIdx(userService.getIdxByUserId(userId));
                return tokenProvider.create(userLogin);
            } else {
                return "비밀번호를 확인하세요";
            }
        } catch (Exception e) {
            return "아이디를 확인하세요";
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
