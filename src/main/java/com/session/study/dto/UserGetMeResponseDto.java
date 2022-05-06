package com.session.study.dto;

import com.session.study.domain.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserGetMeResponseDto {
    private String email;
    private String name;

    public static UserGetMeResponseDto build(User user) {
        return UserGetMeResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
