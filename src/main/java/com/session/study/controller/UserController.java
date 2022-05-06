package com.session.study.controller;

import com.session.study.domain.User;
import com.session.study.dto.UserGetMeResponseDto;
import com.session.study.dto.UserLoginRequestDto;
import com.session.study.dto.UserSignupRequestDto;
import com.session.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    private final static String SESSION_KEY = "USER_ID";

    @PostMapping("/signup")
    public void signup(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        userService.signup(userSignupRequestDto.toEntity(passwordEncoder));
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        User loginUser = userService.login(userLoginRequestDto.toEntity(), passwordEncoder);
        httpSession.setAttribute(SESSION_KEY, loginUser.getId());
    }

    @GetMapping("/me")
    public UserGetMeResponseDto getMe() {
        Long userId = (Long) httpSession.getAttribute(SESSION_KEY);
        User user = userService.findUser(userId);
        return UserGetMeResponseDto.build(user);
    }

    @PostMapping("/logout")
    public void logout() {
        httpSession.removeAttribute(SESSION_KEY);
    }
}
