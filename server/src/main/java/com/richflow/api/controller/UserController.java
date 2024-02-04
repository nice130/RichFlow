package com.richflow.api.controller;

import com.richflow.api.domain.User;
import com.richflow.api.request.UserLogin;
import com.richflow.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String doLogin(@RequestBody UserLogin userLogin) {
        try {
            if (userService.getByCredentials(userLogin.getMemberId(), userLogin.getMemberPassword())) {
                return "Login Success";
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
    public ResponseEntity<?> join(@RequestBody UserLogin userLogin) {
        try {
            User user = userService.createUser(userLogin);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
