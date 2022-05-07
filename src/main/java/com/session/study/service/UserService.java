package com.session.study.service;

import com.session.study.domain.User;
import com.session.study.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(User user) {
        userRepository.save(user);
    }

    public User login(User loginUser) {
        User findUser = userRepository.findByEmail(loginUser.getEmail()).orElseThrow(EntityNotFoundException::new);
        if (!findUser.getPassword().equals(loginUser.getPassword())) {
            throw new IllegalArgumentException("패스워드가 잘못되었습니다.");
        }
        return findUser;
    }

    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
