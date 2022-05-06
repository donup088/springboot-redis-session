package com.session.study.dto;

import com.session.study.domain.User;
import lombok.Getter;

@Getter
public class UserLoginRequestDto {
    public String email;
    public String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .build();
    }
}
