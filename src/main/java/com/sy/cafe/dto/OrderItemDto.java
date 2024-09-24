package com.sy.cafe.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDto {
    Long menuId;
    int number;
    Long price;

    @Builder
    public OrderItemDto(Long menuId, int number, Long price) {
        this.menuId = menuId;
        this.number = number;
        this.price = price;
    }
}
