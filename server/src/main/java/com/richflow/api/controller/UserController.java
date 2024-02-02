package com.convenientBook.api.controller;

import com.convenientBook.api.domain.User;
import com.convenientBook.api.request.UserCreate;
import com.convenientBook.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public String doLogin(@RequestBody UserCreate userCreate) {
        try {
            if (userService.getByCredentials(userCreate.getMemberId(), userCreate.getMemberPassword())) {
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
    public User join(@RequestBody UserCreate userCreate) {
        try {
            User user = userService.createUser(userCreate);
            log.info("응 성공");
            return user;
        } catch (Exception e) {
            log.error("응 실패");
            return new User();
        }
    }
}
