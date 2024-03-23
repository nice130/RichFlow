package com.richflow.api.controller.user;

import com.richflow.api.domain.user.User;
import com.richflow.api.request.user.UserRequest;
import com.richflow.api.response.user.UserResponse;
import com.richflow.api.security.TokenProvider;
import com.richflow.api.service.accountingTypes.AccountingTypesService;
import com.richflow.api.service.accounts.AccountsService;
import com.richflow.api.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final AccountingTypesService accountingTypesService;
    private final AccountsService accountsService;


    @PostMapping("/login")
    public UserResponse doLogin(@RequestBody UserRequest userRequest) {
        try {
            String userId = userRequest.getUserId();
            // 아이디 확인
            if (!userService.getExistsByUserId(userId)) {
                int code = 504; // 아이디가 없습니다.
                return UserService.buildUserResponse(code);
            }
            // 아이디, 비밀번호 검증
            if (userService.getByCredentials(userId, userRequest.getUserPassword())) {
                // token 발급
                userRequest.setUserIdx(userService.getUserIdxByUserId(userId));
                String token = tokenProvider.create(userRequest);

                // data set
                HashMap<String, Object> data = new HashMap<>();
                data.put("token", token);
                data.put("nickname", userService.getUserNicknameByUserId(userId));

                int code = 200; // 성공
                return UserService.buildUserResponse(code, data);
            } else {
                int code = 501; // 비밀번호를 확인하세요
                return UserService.buildUserResponse(code);
            }
        } catch (Exception e) {
            log.info(String.valueOf(e));
            int code = 600;
            return UserService.buildUserResponse(code, String.valueOf(e));
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/join")
    public UserResponse join(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        try {
            String userId = userRequest.getUserId();
            if(userService.getExistsByUserId(userId)) {
                int code = 503; // 이미 존재하는 아이디입니다.
                return UserService.buildUserResponse(code);
            }
            // 회원 생성
            User user = userService.createUser(userRequest);
            userRequest.setUserIdx(user.getUserIdx());

            // 회원 로그 입력
            userRequest.setUslgUpdateIp(request.getRemoteAddr());
            userService.setUserLog(userRequest);

            String token = tokenProvider.create(userRequest);

            HashMap<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("nickname", user.getUserNickname());

            int code = 200; // 성공
            return UserService.buildUserResponse(code, data);
        } catch (Exception e) {
            log.info(String.valueOf(e));
            int code = 600;
            return UserService.buildUserResponse(code, String.valueOf(e));
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
