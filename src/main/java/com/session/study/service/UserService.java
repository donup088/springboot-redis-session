package com.session.study.service;

import com.session.study.domain.User;
import com.session.study.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(User user) {
        userRepository.save(user);
    }
}
