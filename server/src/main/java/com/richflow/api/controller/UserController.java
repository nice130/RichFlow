package com.richflow.api.controller;

import com.richflow.api.domain.User;
import com.richflow.api.request.UserLogin;
import com.richflow.api.service.UserService;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/login")
    public String loginChk(HttpSession session) {
        String id = (String) session.getAttribute("userId");
        if(id != null) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String doLogin(@RequestBody UserLogin userLogin, HttpSession session) {
        try {
            if (userService.getByCredentials(userLogin.getMemberId(), userLogin.getMemberPassword())) {
                session.setAttribute("userId", userLogin.getMemberId());
                return "redirect:/";
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
    public String join(@RequestBody UserLogin userLogin, HttpSession session) {
        try {
            String id = (String) session.getAttribute("userId");
            if(id != null) {
                return "redirect:/";
            } else {
                User user = userService.createUser(userLogin);
                return "redirect:/";
            }
        } catch (Exception e) {
            return "redirect:/join?result=fail";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
