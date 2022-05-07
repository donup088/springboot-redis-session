package com.session.study.dto;

import com.session.study.domain.User;
import lombok.Getter;

@Getter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String name;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
