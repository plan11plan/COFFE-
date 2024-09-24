package com.sy.cafe.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointResponseDto {
    Long userId;
    Long point;

    public PointResponseDto(Long id, Long point) {
        this.userId = id;
        this.point = point;
    }
}
