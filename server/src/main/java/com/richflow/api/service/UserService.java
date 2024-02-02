package com.convenientBook.api.service;

import com.convenientBook.api.domain.User;
import com.convenientBook.api.repository.UserRepository;
import com.convenientBook.api.request.UserCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserCreate userCreate) {
        User user = new User();
        user.setMemberId(userCreate.getMemberId());
        user.setMemberPassword(userCreate.getMemberPassword());
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean getByCredentials(final String id, final String password) {
        return getPasswordById(id).equals(password);
    }

    public String getPasswordById(String id) {
        User user = userRepository.findByMemberId(id);
        return user.getMemberPassword();
    }

}
