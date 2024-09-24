package com.sy.cafe.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PopularMenuDto {
    private Long id;
    private String name;
    private int orderNum;

    public PopularMenuDto(Long id, String name, int orderNum) {
        this.id = id;
        this.name = name;
        this.orderNum = orderNum;
    }
}
