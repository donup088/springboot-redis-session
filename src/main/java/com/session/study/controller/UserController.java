package com.session.study.controller;

import com.session.study.domain.User;
import com.session.study.dto.UserGetMeResponseDto;
import com.session.study.dto.UserLoginRequestDto;
import com.session.study.dto.UserSignupRequestDto;
import com.session.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;
    private final static String SESSION_KEY = "USER_ID";

    @PostMapping("/signup")
    public void signup(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        userService.signup(userSignupRequestDto.toEntity());
    }

    @PostMapping("/login")
    public void login(HttpSession httpSession, @RequestBody UserLoginRequestDto userLoginRequestDto) {
        User loginUser = userService.login(userLoginRequestDto.toEntity());
        httpSession.setAttribute(SESSION_KEY, loginUser.getId());
        httpSession.setAttribute("name", "test");
        log.info("httpSession 아이디: " + httpSession.getId());
    }

    @GetMapping("/me")
    public UserGetMeResponseDto getMe() {
        Long userId = (Long) httpSession.getAttribute(SESSION_KEY);
        User user = userService.findUser(userId);
        return UserGetMeResponseDto.build(user);
    }

    @GetMapping("/logout")
    public void logout() {
        httpSession.removeAttribute(SESSION_KEY);
    }

    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }
}
