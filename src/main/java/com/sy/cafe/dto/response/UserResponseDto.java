package com.sy.cafe.dto.response;

import com.sy.cafe.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
    Long userId;
    String nickname;
    Long point;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.point = user.getPoint();
    }
}
